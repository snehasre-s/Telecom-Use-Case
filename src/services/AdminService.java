package services;

import model.Customer;
import model.Plan;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Customer> viewAllCustomers();
    List<Plan> viewAllPlans();
    void updatePlan(int planId, Plan updatedPlan);
    Map<String, Double> generateAnalyticsReport();
}

