package services;

import java.util.Map;

public interface AnalyticsService {
    void topNDataUsers(int N);
    Map<Integer, Double> arpuByPlan();
    void overageDistribution();
    void detectCreditRisk();
    void recommendPlans();
}
