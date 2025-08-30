package services;


import model.Plan;
import java.util.List;

public interface PlanService {

    List<Plan> viewAllPlans();

    Plan getPlanById(int planId);

    void addPlan(Plan plan);

    void updatePlan(int planId, Plan updatedPlan);

    void deletePlan(int planId);
}

