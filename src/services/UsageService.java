package services;

import model.Usage;

import java.time.LocalDateTime;
import java.util.List;

public interface UsageService {
    Usage getUsageByCustomerId(int customerId);

    List<Usage> getAllUsages();

    String addUsage(int subscriptionId, double dataGb, int voiceMinutes, int smsCount, boolean roaming, boolean international, LocalDateTime usageTime);
}
