package presentation;

import model.*;
import repo.AdminSeeder;
import repo.CustomerSeeder;
import repo.PlanSeeder;
import repo.UsageSeeder;
import services.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Customer> customers = CustomerSeeder.seedCustomers();
        List<Admin> admins = AdminSeeder.seedAdmins();


        CustomerService customerService = new CustomerServiceImpl();
        PlanService planService = new PlanServiceImpl();
        SubscriptionService subscriptionService = new SubscriptionServiceImpl();
        UsageService usageService = new UsageServiceImpl();
        InvoiceService invoiceService = new InvoiceServiceImpl();
        AnalyticsService analyticsService = new AnalyticsServiceImpl();

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
            System.out.println("✅ Logged in as ADMIN");
            showAdminMenu(scanner, customerService, planService, subscriptionService, analyticsService);
            return;
        }

        Customer customerMatch = customers.stream()
                .filter(c -> (c.getPhone().equals(username) || c.getPhone().equals(username))
                        && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (customerMatch != null) {
            System.out.println("✅ Logged in as CUSTOMER: " + customerMatch.getName());
            showCustomerMenu(scanner, customerService, invoiceService);
            return;
        }

        // 3. If nothing matched
        System.out.println("❌ Invalid credentials. Login failed!");
    }

    private static void showAdminMenu(Scanner scanner, CustomerService cs, PlanService ps, SubscriptionService ss, AnalyticsService as) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View all customers");
            System.out.println("2. View all plans");
            System.out.println("3. Add a plan");
            System.out.println("4. Update plan");
            System.out.println("5. Analytics Reports");
            System.out.println("6. Exit admin menu");
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

                        System.out.println("✅ Plan added: " + newPlan.getName());
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
                        analyticsMenu(scanner,as);
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
    private static void analyticsMenu(Scanner scanner, AnalyticsService as) {
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
    private static void showCustomerMenu(Scanner scanner, CustomerService cs, InvoiceService is) {
        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Register new customer");
            System.out.println("2. View your invoice");
            System.out.println("3. Update your details");
            System.out.println("4. Exit customer menu");
            System.out.print("Choose an option: ");

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
                        System.out.print("Enter your Customer ID: ");
                        int custId = Integer.parseInt(scanner.nextLine().trim());
                        Invoice invoice = is.displayInvoice(custId);
                        if (invoice == null) {
                            System.out.println("No invoice found for given Customer ID.");
                        } else {
                            System.out.println(invoice);
                        }
                        break;

                    case "3":
                        System.out.print("Enter your Customer ID: ");
                        int updId = Integer.parseInt(scanner.nextLine().trim());

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

                        cs.updateCustomerDetails(updId, field, newValue);
                        System.out.println("Details updated successfully.");
                        break;

                    case "4":
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
