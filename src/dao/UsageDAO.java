package dao;

import model.Usage;

import java.util.List;

public interface UsageDAO {
    Usage getUsageByCustomerId(int customerId);

    List<Usage> getAllUsages();

    void addUsage(int customerId, double data, int voice, int sms);
}
