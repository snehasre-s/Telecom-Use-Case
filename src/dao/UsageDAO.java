package dao;

import model.Usage;

import java.time.LocalDateTime;
import java.util.List;

public interface UsageDAO {
    Usage getUsageByCustomerId(int customerId);

    List<Usage> getAllUsages();

    String addUsage(int subscriptionId, double dataGb, int voiceMinutes, int smsCount, boolean roaming, boolean international, LocalDateTime usageTime);
}
