import model.Plan;
import services.PlanService;
import services.PlanServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
    }
}