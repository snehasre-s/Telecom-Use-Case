package dao;

import model.Usage;
import repo.UsageSeeder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class UsageDAOImpl implements UsageDAO{

    private List<Usage> usageRecords;

    public void UsageDAO(){
        this.usageRecords= UsageSeeder.seedUsage();
    }
    @Override
    public Usage getUsageByCustomerId(int customerId) {
        Stream<Usage> filteredRecords = usageRecords.stream()
                .filter(u -> u.getSubscriptionId() == customerId);

        return (Usage) filteredRecords;
    }

    @Override
    public List<Usage> getAllUsages() {
        return new ArrayList<>(usageRecords);
    }

    @Override
    public String addUsage(int subscriptionId, double dataGb,int voiceMinutes,int smsCount,boolean roaming, boolean international,LocalDateTime usageTime) {
        usageRecords.add(new Usage(subscriptionId,dataGb,voiceMinutes,smsCount,roaming,international,usageTime));
        return "Added Successfully";


    }
}
