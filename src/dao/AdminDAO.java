package dao;


import model.Customer;
import model.Plan;
import repo.CustomerSeeder;
import repo.PlanSeeder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AdminDAO {

    List<Customer> viewAllCustomers();

    List<Plan> viewAllPlans();

    void updatePlan(int planId, Plan updatedPlan);

    Map<String, Double> generateAnalyticsReport();
}

