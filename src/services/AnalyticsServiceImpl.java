package services;
import repo.PlanSeeder;
import model.Invoice;
import repo.Invoices;
import model.Plan;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsServiceImpl implements AnalyticsService {

    @Override
    public void topNDataUsers(int N) {
        System.out.println("\n Top " + N + " Data Users (by overage amount as proxy):");

        Invoices.getInvoices().stream()
                .sorted(Comparator.comparingDouble(Invoice::getOverageAmount).reversed())
                .limit(N)
                .forEach(inv -> System.out.println(
                        "CustomerID: " + inv.getCustomerId() +
                                ", PlanID: " + inv.getPlanId() +
                                ", Overage: " + inv.getOverageAmount()
                ));
    }

    @Override
    public Map<Integer, Double> arpuByPlan() {
        System.out.println("\n ARPU by Plan:");

        // Group invoices by planId and average totalAmount
        Map<Integer, Double> arpuMap = Invoices.getInvoices().stream()
                .collect(Collectors.groupingBy(
                        Invoice::getPlanId,
                        Collectors.averagingDouble(Invoice::getTotalAmount)
                ));

        arpuMap.forEach((planId, arpu) ->
                System.out.println("PlanID: " + planId + " → ARPU: " + arpu)
        );

        return arpuMap;
    }

    @Override
    public void overageDistribution() {
        System.out.println("\n Overage Distribution (count, sum, average):");

        DoubleSummaryStatistics stats = Invoices.getInvoices().stream()
                .collect(Collectors.summarizingDouble(Invoice::getOverageAmount));

        System.out.println("Count: " + stats.getCount());
        System.out.println("Total Overage: " + stats.getSum());
        System.out.println("Average Overage: " + stats.getAverage());
    }

    @Override
    public void detectCreditRisk() {
        System.out.println("\nCredit Risk Detection (unpaid > 60 days):");

        LocalDate today = LocalDate.now();

        Invoices.getInvoices().stream()
                .filter(inv -> !inv.isPaid())
                .filter(inv -> ChronoUnit.DAYS.between(inv.getInvoiceDate(), today) > 60)
                .forEach(inv -> System.out.println(
                        "⚠CustomerID: " + inv.getCustomerId() +
                                " InvoiceID: " + inv.getInvoiceId() +
                                " is at credit risk (unpaid since " + inv.getInvoiceDate() + ")"
                ));
    }

    @Override
    public void recommendPlans() {
        System.out.println("\nPlan Recommendations based on overages:");

        // Load all plans
        List<Plan> plans = PlanSeeder.seedPlans();

        // Group invoices by customer to get average overage
        Map<Integer, Double> avgOverageByCustomer = Invoices.getInvoices().stream()
                .collect(Collectors.groupingBy(
                        Invoice::getCustomerId,
                        Collectors.averagingDouble(Invoice::getOverageAmount)
                ));

        for (Integer custId : avgOverageByCustomer.keySet()) {
            double avgOverage = avgOverageByCustomer.get(custId);
            if (avgOverage > 0) { // threshold for recommending a better plan
                // Find current plan ID from latest invoice
                Invoice latestInvoice = Invoices.getInvoices().stream()
                        .filter(inv -> inv.getCustomerId() == custId)
                        .reduce((first, second) -> second) // get last invoice
                        .orElse(null); // TODO- Should throw exception

                if (latestInvoice == null) continue;

                int currentPlanId = latestInvoice.getPlanId();
                Plan currentPlan = plans.stream()
                        .filter(p -> p.getId() == currentPlanId)
                        .findFirst()
                        .orElse(null);

                if (currentPlan == null) continue;

                // Recommend the next higher plan based on base rental or allowances
                Plan recommended = plans.stream()
                        .filter(p -> p.getMonthlyRental() > currentPlan.getMonthlyRental()) // higher plan
                        .sorted((p1, p2) -> Double.compare(p1.getMonthlyRental(), p2.getMonthlyRental()))
                        .findFirst()
                        .orElse(null);

                if (recommended != null) {
                    System.out.println("CustomerID: " + custId +
                            " (Avg overage: " + avgOverage + ") → Recommend Plan: " +
                            recommended.getName() + " (₹" + recommended.getMonthlyRental() + ")");
                } else {
                    System.out.println("CustomerID: " + custId +
                            " (Avg overage: " + avgOverage + ") → Already on highest plan!");
                }
            }
        }
    }

}
