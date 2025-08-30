package presentation;

import model.Admin;
import model.Customer;
import model.Plan;
import model.Usage;
import repo.AdminSeeder;
import repo.CustomerSeeder;
import repo.PlanSeeder;
import repo.UsageSeeder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Admin> admins = AdminSeeder.seedAdmins();
        List<Customer> customers = CustomerSeeder.seedCustomers();
        List<Plan> plans = PlanSeeder.seedPlans();
        List<Usage> usageRecords = UsageSeeder.seedUsage();
    }
}