package dao;

import model.Plan;
import repo.PlanSeeder;

import java.util.ArrayList;
import java.util.List;

public class PlanDAOImpl implements PlanDAO{

    private final List<Plan> plans;

    public PlanDAOImpl() {
        this.plans = new ArrayList<>(PlanSeeder.seedPlans());
    }
    @Override
    public List<Plan> getAllPlans() {
        return new ArrayList<>(plans);
    }

    @Override
    public Plan getPlanById(int id) {
        return plans.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addPlan(Plan plan) {
        plans.add(plan);
    }

    @Override
    public void updatePlan(int planId, Plan updatedPlan) {
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getId() == planId) {
                plans.set(i, updatedPlan);
                return;
            }
        }
    }

    @Override
    public void deletePlan(int planId) {
        plans.removeIf(p -> p.getId() == planId);
    }
}
