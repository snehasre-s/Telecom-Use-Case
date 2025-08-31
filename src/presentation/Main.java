package presentation;

import model.Admin;
import model.Customer;
import model.Plan;
import model.Usage;
import repo.AdminSeeder;
import repo.CustomerSeeder;
import repo.PlanSeeder;
import repo.UsageSeeder;
import services.PlanService;
import services.PlanServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlanService planService = new PlanServiceImpl();

        System.out.println("=== All Available Plans ===");
        planService.viewAllPlans().forEach(System.out::println);

        System.out.println("\n=== Add a New Plan ===");
        Plan newPlan = new Plan(4, "Student Saver", 149,
                1.0, 50, 20,
                15.0, 2.0, 1.0,
                1.0, false,
                20.0, 2.0,
                false, 0.0, false);

        planService.addPlan(newPlan);

        System.out.println("\n=== Plans After Adding New One ===");
        planService.viewAllPlans().forEach(System.out::println);

        // 2. Get a plan by ID
        System.out.println("\n---- Get Plan by ID ----");
        Plan p = planService.getPlanById(2);
        if (p != null) {
            System.out.println("Found: " + p);
        } else {
            System.out.println("Plan not found!");
        }

        // 3. Update a plan
        System.out.println("\n---- Update Plan ----");
        Plan updatedPlan = new Plan(
                2,
                "Family Connect Plus",  // changed name
                599.0,                  // changed rental
                15.0,
                800,
                200,
                7.5,
                0.7,
                0.25,
                6.0,
                true,
                10.0,
                1.2,
                true,
                25.0,
                true
        );

        planService.updatePlan(2, updatedPlan);

        // Show updated plan
        System.out.println("After update: " + planService.getPlanById(2));

        // 4. Delete a plan
        System.out.println("\n---- Delete Plan ----");
        planService.deletePlan(1);  // delete plan with ID 1

        // Show all plans after deletion
        System.out.println("After deletion:");
        planService.viewAllPlans().forEach(System.out::println);
    }
}