package repo;


import model.Plan;
import java.util.ArrayList;
import java.util.List;

public class PlanSeeder {

    public static List<Plan> seedPlans() {
        List<Plan> plans = new ArrayList<>();

        plans.add(new Plan(
                101,
                "Basic Saver",
                199.0,
                2.0,  // Data Allowance (GB)
                100,  // Voice Allowance (Min)
                50,   // SMS Allowance
                10.0, // Data Overage Rate (₹/GB)
                1.0,  // Voice Overage Rate (₹/Min)
                0.5,  // SMS Overage Rate (₹/SMS)
                1.5,  // FUP Limit (GB)
                false, // Rollover Enabled
                15.0,  // Roaming Charge per GB
                2.0,   // Roaming Charge per Min
                false, // Family Shared
                0.0,   // Family Data Pool GB
                false  // Weekend Free Voice
        ));

        plans.add(new Plan(
                102,
                "Family Connect",
                499.0,
                10.0,
                500,
                100,
                8.0,
                0.8,
                0.3,
                5.0,
                true,
                12.0,
                1.5,
                true,
                20.0,
                true
        ));

        plans.add(new Plan(
                103,
                "Premium Unlimited",
                999.0,
                50.0,
                2000,
                500,
                5.0,
                0.5,
                0.2,
                20.0,
                true,
                8.0,
                1.0,
                false,
                0.0,
                true
        ));

        return plans;
    }
}


