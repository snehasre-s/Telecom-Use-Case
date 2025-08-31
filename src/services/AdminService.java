package services;

import model.Customer;
import model.Plan;

import java.util.List;
import java.util.Map;

public interface AdminService {
    List<Customer> viewAllCustomers();
    List<Plan> viewAllPlans();
    Plan updatePlan(int planId, Plan updatedPlan);
    Plan addNewPlan(Plan plan);
    Map<String, Double> generateAnalyticsReport();
}

