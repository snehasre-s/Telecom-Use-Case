package dao;

import model.*;
import repo.CustomerSeeder;
import repo.PlanSeeder;
import repo.SubscriptionSeeder;
import repo.UsageSeeder;
import repo.Invoices;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InvoiceDAOImpl implements InvoiceDAO {

    @Override
    public List<Invoice> displayInvoice(int customerId) {
        List<Invoice> allInvoice = Invoices.getInvoices().stream().filter(i->i.getCustomerId()==customerId).toList();
        System.out.println("All invoices : ");
        allInvoice.forEach(System.out::println);
        return allInvoice;
    }

    @Override
    public Invoice generateNewInvoice(int customerId) {
        List<Customer> customers = CustomerSeeder.seedCustomers();
        List<Plan> plans = PlanSeeder.seedPlans();
        List<Subscription> subs = SubscriptionSeeder.seedSubscriptions();
        List<Usage> usages = UsageSeeder.seedUsage();

        Customer customer = customers.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));

        Subscription subscription = subs.stream()
                .filter(s -> s.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subscription not found for customer " + customerId));

        Plan plan = plans.stream()
                .filter(p -> p.getId() == subscription.getPlanId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Plan not found for subscription " + subscription.getSubscriptionId()));

        if (plan.isFamilyShared()) {
            return generateFamilyInvoices(subscription.getFamilyId(), plan, subs, usages, customerId);
        } else {
            return generateIndividualInvoice(customer, subscription, plan, usages);
        }
    }

    private Invoice generateIndividualInvoice(Customer c, Subscription s, Plan p, List<Usage> allUsages) {
        List<Usage> custUsage = allUsages.stream()
                .filter(u -> u.getSubscriptionID() == s.getCustomerId())
                .collect(Collectors.toList());

        double baseRental = p.getMonthlyRental();
        double overage = calculateOverage(p, custUsage);
        double roaming = calculateRoamingCharges(p, custUsage);
        double discounts = applyReferralDiscount(c);
        double rolloverCredit = applyRolloverData(p, custUsage);

        //Currently rollover credit is not used, Need to update the implementation
        double total = baseRental + overage + roaming - discounts;

        return Invoices.createInvoice(
                c.getCustomerId(),
                s.getSubscriptionId(),
                p.getId(),
                baseRental,
                overage + roaming,
                total,
                LocalDate.now(),
                false,
                null
        );
    }

    private Invoice generateFamilyInvoices(String familyId, Plan plan, List<Subscription> allSubs, List<Usage> allUsages, int requestedCustomerId) {
        List<Subscription> familySubs = allSubs.stream()
                .filter(s -> familyId.equals(s.getFamilyId()))
                .collect(Collectors.toList());

        if (familySubs.isEmpty()) {
            throw new RuntimeException("No subscriptions found for family " + familyId);
        }

        double totalDataAllowance = plan.getDataAllowanceGb() + plan.getFamilyDataPoolGb();
        int totalVoiceAllowance = plan.getVoiceAllowanceMin();
        int totalSmsAllowance = plan.getSmsAllowance();

        List<Usage> familyUsages = allUsages.stream()
                .filter(u -> familySubs.stream().anyMatch(s -> s.getCustomerId() == u.getSubscriptionID()))
                .collect(Collectors.toList());

        double familyUsedData = familyUsages.stream().mapToDouble(Usage::getDataGb).sum();
        int familyUsedVoice = familyUsages.stream().mapToInt(Usage::getVoiceMinutes).sum();
        int familyUsedSms = familyUsages.stream().mapToInt(Usage::getSmsCount).sum();

        // Calculate 60% cap
        double maxDataPerPerson = totalDataAllowance * 0.6;
        int maxVoicePerPerson = (int) (totalVoiceAllowance * 0.6);
        int maxSmsPerPerson = (int) (totalSmsAllowance * 0.6);

        Invoice requestedInvoice = null;

        for (Subscription sub : familySubs) {
            Customer customer = CustomerSeeder.seedCustomers().stream()
                    .filter(c -> c.getCustomerId() == sub.getCustomerId())
                    .findFirst()
                    .orElseThrow();

            List<Usage> memberUsage = familyUsages.stream()
                    .filter(u -> u.getSubscriptionID() == sub.getSubscriptionId())
                    .collect(Collectors.toList());

            double memberData = memberUsage.stream().mapToDouble(Usage::getDataGb).sum();
            int memberVoice = memberUsage.stream().mapToInt(Usage::getVoiceMinutes).sum();
            int memberSms = memberUsage.stream().mapToInt(Usage::getSmsCount).sum();

            // --- Overages ---
            double dataOver = 0, voiceOver = 0, smsOver = 0;

            // Rule 1: individual 60% cap
            if (memberData > maxDataPerPerson) {
                dataOver += (memberData - maxDataPerPerson) * plan.getDataOverageRate();
            }
            if (memberVoice > maxVoicePerPerson) {
                voiceOver += (memberVoice - maxVoicePerPerson) * plan.getVoiceOverageRate();
            }
            if (memberSms > maxSmsPerPerson) {
                smsOver += (memberSms - maxSmsPerPerson) * plan.getSmsOverageRate();
            }

            // Rule 2: family exceeded allowance
            if (familyUsedData > totalDataAllowance) {
                dataOver += (memberData / familyUsedData) * (familyUsedData - totalDataAllowance) * plan.getDataOverageRate();
            }
            if (familyUsedVoice > totalVoiceAllowance) {
                voiceOver += (memberVoice / (double) familyUsedVoice) * (familyUsedVoice - totalVoiceAllowance) * plan.getVoiceOverageRate();
            }
            if (familyUsedSms > totalSmsAllowance) {
                smsOver += (memberSms / (double) familyUsedSms) * (familyUsedSms - totalSmsAllowance) * plan.getSmsOverageRate();
            }

            double roaming = calculateRoamingCharges(plan, memberUsage);
            double baseRental = plan.getMonthlyRental(); // each pays base rental
            double total = baseRental + dataOver + voiceOver + smsOver + roaming;

            Invoice inv = Invoices.createInvoice(
                    customer.getCustomerId(),
                    sub.getSubscriptionId(),
                    plan.getId(),
                    baseRental,
                    dataOver + voiceOver + smsOver + roaming,
                    total,
                    LocalDate.now(),
                    false,
                    familyId
            );

            if (customer.getCustomerId() == requestedCustomerId) {
                requestedInvoice = inv;
            }
        }

        return requestedInvoice;
    }

    // Helper methods
    private static double calculateOverage(Plan p, List<Usage> usage) {
        double totalData = usage.stream().mapToDouble(Usage::getDataGb).sum();
        int totalVoice = usage.stream().mapToInt(Usage::getVoiceMinutes).sum();
        int totalSms = usage.stream().mapToInt(Usage::getSmsCount).sum();

        double dataOver = Math.max(0, totalData - p.getDataAllowanceGb()) * p.getDataOverageRate();
        double voiceOver = Math.max(0, totalVoice - p.getVoiceAllowanceMin()) * p.getVoiceOverageRate();
        double smsOver = Math.max(0, totalSms - p.getSmsAllowance()) * p.getSmsOverageRate();

        return dataOver + voiceOver + smsOver;
    }

    private static double calculateRoamingCharges(Plan p, List<Usage> usage) {
        double roamingData = usage.stream()
                .filter(Usage::isRoaming)
                .mapToDouble(Usage::getDataGb)
                .sum();

        int roamingVoice = usage.stream()
                .filter(Usage::isRoaming)
                .mapToInt(Usage::getVoiceMinutes)
                .sum();

        return (roamingData * p.getRoamingChargePerGb()) +
                (roamingVoice * p.getRoamingChargePerMin());
    }

    private static double applyReferralDiscount(Customer c) {
        if (c.getReferral_stat() != null && !c.getReferral_stat().equalsIgnoreCase("none")) {
            return 50.0; // flat discount of 50
        }
        return 0.0;
    }

    private static double applyRolloverData(Plan p, List<Usage> usage) {
        if (!p.isRolloverEnabled()) return 0.0;

        double totalUsed = usage.stream().mapToDouble(Usage::getDataGb).sum();
        double unused = p.getDataAllowanceGb() - totalUsed;
        return unused > 0 ? unused * 5 : 0.0; // ₹5 per GB unused credit
    }
}
