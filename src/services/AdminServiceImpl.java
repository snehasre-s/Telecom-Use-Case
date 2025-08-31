package services;

import dao.AdminDAO;
import dao.AdminDAOImpl;
import model.Customer;
import model.Plan;

import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService{
    @Override
    public List<Customer> viewAllCustomers() {
        AdminDAO adminAdoObj = new AdminDAOImpl();
        return adminAdoObj.viewAllCustomers();
    }

    @Override
    public List<Plan> viewAllPlans() {
        AdminDAO adminAdoObj = new AdminDAOImpl();
        return adminAdoObj.viewAllPlans();
    }

    @Override
    public Plan updatePlan(int planId, Plan updatedPlan) {
        AdminDAO adminAdoObj = new AdminDAOImpl();
        return adminAdoObj.updatePlan(planId, updatedPlan);
    }

    @Override
    public Plan addNewPlan(Plan plan) {
        AdminDAO adminAdoObj = new AdminDAOImpl();
        return adminAdoObj.addNewPlan(plan);
    }

    @Override
    public Map<String, Double> generateAnalyticsReport() {
        return Map.of();
    }
}
