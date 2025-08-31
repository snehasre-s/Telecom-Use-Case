package services;

import dao.PlanDAO;
import dao.PlanDAOImpl;
import model.Plan;

import java.util.List;

public class PlanServiceImpl implements PlanService {

    private final PlanDAO planDAO;

    public PlanServiceImpl() {
        this.planDAO = new PlanDAOImpl(); // uses seeded data internally
    }

    @Override
    public List<Plan> viewAllPlans() {
        return planDAO.getAllPlans();
    }

    @Override
    public Plan getPlanById(int planId) {
        return planDAO.getPlanById(planId);
    }

    @Override
    public void addPlan(Plan plan) {
        if (planDAO.getPlanById(plan.getId()) == null) {
            planDAO.addPlan(plan);
        } else {
            System.out.println("Plan with ID " + plan.getId() + " already exists!");
        }
    }

    @Override
    public void updatePlan(int planId, Plan updatedPlan) {
        planDAO.updatePlan(planId, updatedPlan);
    }

    @Override
    public void deletePlan(int planId) {
        planDAO.deletePlan(planId);
    }
}

