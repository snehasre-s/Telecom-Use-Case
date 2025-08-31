package dao;

import model.Customer;
import model.Plan;

import java.util.List;
import java.util.Map;

public class AdminDAOImpl implements AdminDAO{
    @Override
    public List<Customer> viewAllCustomers() {
        return List.of();
    }

    @Override
    public List<Plan> viewAllPlans() {
        return List.of();
    }

    @Override
    public void updatePlan(int planId, Plan updatedPlan) {

    }

    @Override
    public Map<String, Double> generateAnalyticsReport() {
        return Map.of();
    }
}
