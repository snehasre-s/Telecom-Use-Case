package dao;

import model.Plan;

import java.util.List;

public interface PlanDAO {

    List<Plan> getAllPlans();

    Plan getPlanById(int planId);

    void addPlan(Plan plan);

    void updatePlan(int planId, Plan updatedPlan);

    void deletePlan(int planId);
}

