package presentation;

import exceptions.SubscriptionNotFoundException;
import model.*;
import repo.AdminSeeder;
import repo.CustomerSeeder;
import services.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Customer> customers = CustomerSeeder.seedCustomers();
        List<Admin> admins = AdminSeeder.seedAdmins();

        System.out.println("==== Welcome ====");
        System.out.print("Enter phone/email: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Admin adminMatch = admins.stream()
                .filter(a -> a.getPhone().equals(username) && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (adminMatch != null) {
            System.out.println("Logged in as ADMIN");
            showAdminMenu();
            return;
        }

        Customer customerMatch = customers.stream()
                .filter(c -> (c.getPhone().equals(username) || c.getEmail().equals(username))
                        && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (customerMatch != null) {
            System.out.println("Logged in as CUSTOMER: " + customerMatch.getName());
            showCustomerMenu(customerMatch);
            return;
        }

        // 3. If nothing matched
        System.out.println("Invalid credentials. Login failed!");
    }

    private static void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        CustomerService cs = new CustomerServiceImpl();
        PlanService ps = new PlanServiceImpl();
        SubscriptionService ss = new SubscriptionServiceImpl();
        UsageService us = new UsageServiceImpl();
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View all customers");
            System.out.println("2. View all plans");
            System.out.println("3. Add a plan");
            System.out.println("4. Update plan");
            System.out.println("5. Delete a plan");
            System.out.println("6. Add a customer");
            System.out.println("7. View All Subscriptions of a Customer");
            System.out.println("8. View Subscriptions by Family ID");
            System.out.println("9. Update a Subscription");
            System.out.println("10. Get All Usages");
            System.out.println("11. Analytics Reports");
            System.out.println("12. Exit admin menu");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            try {
                switch (input) {
                    case "1":
                        List<Customer> customers = cs.listAllCustomers();
                        if (customers.isEmpty()) {
                            System.out.println("No customers found.");
                        } else {
                            customers.forEach(System.out::println);
                        }
                        break;

                    case "2":
                        List<Plan> plans = ps.viewAllPlans();
                        if (plans.isEmpty()) {
                            System.out.println("No plans available.");
                        } else {
                            plans.forEach(System.out::println);
                        }
                        break;
                    case "3":
                        System.out.println("--- Add New Plan ---");

                        System.out.print("Enter Plan ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Plan Name: ");
                        String planName = scanner.nextLine();

                        System.out.print("Enter Monthly Rental: ");
                        double monthlyRental = scanner.nextDouble();

                        System.out.print("Enter Data Allowance (GB): ");
                        double dataAllowanceGb = scanner.nextDouble();

                        System.out.print("Enter Voice Allowance (Minutes): ");
                        int voiceAllowanceMin = scanner.nextInt();

                        System.out.print("Enter SMS Allowance: ");
                        int smsAllowance = scanner.nextInt();

                        System.out.print("Enter Data Overage Rate (₹/GB): ");
                        double dataOverageRate = scanner.nextDouble();

                        System.out.print("Enter Voice Overage Rate (₹/Min): ");
                        double voiceOverageRate = scanner.nextDouble();

                        System.out.print("Enter SMS Overage Rate (₹/SMS): ");
                        double smsOverageRate = scanner.nextDouble();

                        System.out.print("Enter FUP Limit (GB): ");
                        double fupLimitGb = scanner.nextDouble();

                        System.out.print("Is Rollover Enabled? (true/false): ");
                        boolean rolloverEnabled = scanner.nextBoolean();

                        System.out.print("Enter Roaming Charge per GB: ");
                        double roamingChargePerGb = scanner.nextDouble();

                        System.out.print("Enter Roaming Charge per Min: ");
                        double roamingChargePerMin = scanner.nextDouble();

                        System.out.print("Is Family Shared Plan? (true/false): ");
                        boolean familyShared = scanner.nextBoolean();

                        double familyDataPoolGb = 0.0;
                        if (familyShared) {
                            System.out.print("Enter Family Data Pool (GB): ");
                            familyDataPoolGb = scanner.nextDouble();
                        }

                        System.out.print("Weekend Free Voice? (true/false): ");
                        boolean weekendFreeVoice = scanner.nextBoolean();

                        Plan newPlan = new Plan(id, planName, monthlyRental, dataAllowanceGb, voiceAllowanceMin, smsAllowance, dataOverageRate, voiceOverageRate, smsOverageRate, fupLimitGb, rolloverEnabled, roamingChargePerGb, roamingChargePerMin, familyShared, familyDataPoolGb, weekendFreeVoice
                        );
                        ps.addPlan(newPlan);

                        System.out.println("Plan added: " + newPlan.getName());
                        break;

                    case "4":
                        System.out.print("Enter Plan ID to update: ");
                        int planId = Integer.parseInt(scanner.nextLine().trim());

                        Plan existingPlan = ps.getPlanById(planId);
                        if (existingPlan == null) {
                            System.out.println("Invalid Plan ID!");
                            break;
                        }

                        System.out.print("New Plan Name (currently: " + existingPlan.getName() + "): ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) name = existingPlan.getName();

                        System.out.print("New Monthly Rental (currently: " + existingPlan.getMonthlyRental() + "): ");
                        String rentalStr = scanner.nextLine().trim();
                        double rental = rentalStr.isEmpty() ? existingPlan.getMonthlyRental() : Double.parseDouble(rentalStr);

                        existingPlan.setName(name);
                        existingPlan.setMonthlyRental(rental);

                        ps.updatePlan(planId, existingPlan);
                        System.out.println("Plan updated successfully.");
                        break;
                    case "5":
                        System.out.print("Enter Plan ID to delete: ");
                        int planIdToDelete = scanner.nextInt();
                        ps.deletePlan(planIdToDelete);
                        System.out.println("Plan with ID " + planIdToDelete + " deleted successfully.");
                    case "6":
                        System.out.print("Name: ");
                        String custname = scanner.nextLine().trim();
                        if (custname.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");

                        System.out.print("Phone (10 digits): ");
                        String phone = scanner.nextLine().trim();
                        if (!phone.matches("\\d{10}")) throw new IllegalArgumentException("Invalid phone number.");

                        System.out.print("Email: ");
                        String email = scanner.nextLine().trim();
                        if (!email.contains("@") || email.length() < 5) throw new IllegalArgumentException("Invalid email.");

                        System.out.print("Referral Status (e.g. REFERRED or blank): ");
                        String referral = scanner.nextLine().trim();

                        System.out.print("Password: ");
                        String password = scanner.nextLine().trim();
                        if (password.length() < 4) throw new IllegalArgumentException("Password too short.");

                        System.out.print("Plan ID: ");
                        int plaId = Integer.parseInt(scanner.nextLine().trim());

                        Customer newCustomer = new Customer(custname, phone, email, referral, password, plaId, null);
                        cs.registerCustomer(newCustomer);
                        System.out.println("Registration successful. Your Customer ID is " + newCustomer.getCustomerId());
                        break;
                    case "7":
                        try{
                            System.out.print("Enter customer ID: ");
                            int custId = scanner.nextInt();
                            List<Subscription> subs = ss.fetchSubscriptionsByCustomer(custId);
                            subs.forEach(System.out::println);
                        } catch (SubscriptionNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    case "8":
                        System.out.print("Enter family ID: ");
                        String famId = scanner.nextLine();
                        try {
                            List<Subscription> famSubs = ss.fetchSubscriptionsByFamily(famId);
                            famSubs.forEach(System.out::println);
                        } catch (SubscriptionNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "9":
                        System.out.print("Enter subscription ID to update: ");
                        int subId = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            Subscription sub = ss.fetchSubscriptionById(subId);
                            System.out.println("Current subscription: " + sub);

                            // Example: just update planId here
                            System.out.print("Enter new Plan ID: ");
                            int newPlanId = scanner.nextInt();
                            sub.setPlanId(newPlanId);

                            ss.modifySubscription(sub);
                            System.out.println("Subscription updated!");
                        } catch (SubscriptionNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "10":
                        List<Usage> allUsages = us.getAllUsages();
                        System.out.println("--- All Usages ---");
                        allUsages.forEach(System.out::println);
                        break;

                    case "11":
                        analyticsMenu();
                        break;

                    case "12":
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void analyticsMenu() {
        Scanner scanner = new Scanner(System.in);
        AnalyticsService as = new AnalyticsServiceImpl();
        while (true) {
            System.out.println("\n--- Analytics Reports ---");
            System.out.println("1. Top N Data Users");
            System.out.println("2. ARPU by Plan");
            System.out.println("3. Overage Distribution");
            System.out.println("4. Credit Risk Detection");
            System.out.println("5. Plan Recommendations");
            System.out.println("6. Back to Admin Menu");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();
            try {
                switch (input) {
                    case "1":
                        System.out.print("Enter N (number of top users): ");
                        int n = Integer.parseInt(scanner.nextLine().trim());
                        as.topNDataUsers(n);
                        break;

                    case "2":
                        as.arpuByPlan();
                        break;

                    case "3":
                        as.overageDistribution();
                        break;

                    case "4":
                        as.detectCreditRisk();
                        break;

                    case "5":
                        as.recommendPlans();
                        break;

                    case "6":
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void showCustomerMenu(Customer customerMatch) {
        CustomerService cs = new CustomerServiceImpl();
        PlanService ps = new PlanServiceImpl();
        SubscriptionService ss = new SubscriptionServiceImpl();
        UsageService us = new UsageServiceImpl();
        InvoiceService is = new InvoiceServiceImpl();

        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Register new customer");
            System.out.println("2. View your invoice");
            System.out.println("3. Update your details");
            System.out.println("4. View your usage");
            System.out.println("5. Add usage");
            System.out.println("6. View your subscriptions");
            System.out.println("7. View all plans");
            System.out.println("8. Exit customer menu");
            System.out.print("Choose an option: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim();
            try {
                switch (input) {
                    case "1":
                        System.out.print("Name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");

                        System.out.print("Phone (10 digits): ");
                        String phone = scanner.nextLine().trim();
                        if (!phone.matches("\\d{10}")) throw new IllegalArgumentException("Invalid phone number.");

                        System.out.print("Email: ");
                        String email = scanner.nextLine().trim();
                        if (!email.contains("@") || email.length() < 5) throw new IllegalArgumentException("Invalid email.");

                        System.out.print("Referral Status (e.g. REFERRED or blank): ");
                        String referral = scanner.nextLine().trim();

                        System.out.print("Password: ");
                        String password = scanner.nextLine().trim();
                        if (password.length() < 4) throw new IllegalArgumentException("Password too short.");

                        System.out.print("Plan ID: ");
                        int planId = Integer.parseInt(scanner.nextLine().trim());

                        Customer newCustomer = new Customer(name, phone, email, referral, password, planId, null);
                        cs.registerCustomer(newCustomer);
                        System.out.println("Registration successful. Your Customer ID is " + newCustomer.getCustomerId());
                        break;

                    case "2":
                         List<Invoice> allInvoices = is.displayInvoice(customerMatch.getCustomerId());
                        if (allInvoices.size() == 0) {
                            System.out.println("No invoice found for your Customer ID.");
                        } else {
                            allInvoices.forEach(System.out::println);
                        }
                        break;

                    case "3":
                        System.out.print("Field to update (name, phone, email, password): ");
                        String field = scanner.nextLine().trim().toLowerCase();

                        if (!field.equals("name") && !field.equals("phone") && !field.equals("email") && !field.equals("password")) {
                            System.out.println("Invalid field.");
                            break;
                        }

                        System.out.print("New value: ");
                        String newValue = scanner.nextLine().trim();
                        if (newValue.isEmpty()) {
                            System.out.println("New value cannot be empty.");
                            break;
                        }

                        cs.updateCustomerDetails(customerMatch.getCustomerId(), field, newValue);
                        System.out.println("Details updated successfully.");
                        break;

                    case "4":
                        try{
                            List<Usage> u = us.getUsageByCustomerId(customerMatch.getCustomerId());
                            if (u != null) {
                                for(Usage usage:u){
                                    System.out.println(usage);
                                }
                            } else {
                                System.out.println("No usage found for customer.");
                            }
                        }
                        catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            System.out.println("dsfgsd");
                        }

                        break;

                    case "5":
                        System.out.print("Enter Customer ID: ");
                        int custId = scanner.nextInt();

                        System.out.println("Enter Subscription ID:");
                        int subscriptionID=scanner.nextInt();

                        System.out.print("Enter Data used (GB): ");
                        int data = scanner.nextInt();

                        System.out.print("Enter Voice Minutes used: ");
                        int voice = scanner.nextInt();

                        System.out.print("Enter SMS count: ");
                        int sms = scanner.nextInt();

                        System.out.println("Roaming?(True/False)");
                        boolean roaming=scanner.nextBoolean();

                        System.out.println("International?(True/False)");
                        boolean international = scanner.nextBoolean();

                        System.out.println("Enter usageTime");
                        LocalDateTime dateTime = null;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        while (dateTime == null) {
                            try {
                                System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
                                String in = scanner.nextLine();
                                dateTime = LocalDateTime.parse(in, formatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid format! Please enter again.");
                            }
                        }
                        try {
                            us.addUsage(custId,subscriptionID,data, voice, sms,roaming,international,dateTime);
                            System.out.println("Usage added for Customer ID " + custId);
                        } catch (Exception e) {
                            System.out.println("Error while adding usage: " + e.getMessage());
                        }
                        break;

                    case "6":
                        try{
                        List<Subscription> subs = ss.fetchSubscriptionsByCustomer(customerMatch.getCustomerId());
                        subs.forEach(System.out::println);
                        } catch (SubscriptionNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case "7":
                        List<Plan> plans = ps.viewAllPlans();
                        if (plans.isEmpty()) {
                            System.out.println("No plans available.");
                        } else {
                            plans.forEach(System.out::println);
                        }
                        break;

                    case "8":
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
