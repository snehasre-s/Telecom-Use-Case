package dao;

import model.Usage;
import repo.UsageSeeder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsageDAOImpl implements UsageDAO{

    private List<Usage> usageRecords;

    public UsageDAOImpl(){
        this.usageRecords= UsageSeeder.seedUsage();
    }
    @Override
    public List<Usage> getUsageByCustomerId(int customerId) {
        List<Usage> filtered = usageRecords.stream()
                .filter(u -> u.getCustomerID() == customerId)
                .collect(Collectors.toList());
        return filtered;

    }

    @Override
    public List<Usage> getAllUsages() {
        List<Usage> filter = usageRecords.stream().toList();
        return filter;

    }

    @Override
    public String addUsage(int customerID,int subscriptionID,double dataGb,int voiceMinutes,int smsCount,boolean roaming, boolean international,LocalDateTime usageTime) {
        usageRecords.add(new Usage(customerID,subscriptionID,
                dataGb,voiceMinutes,smsCount,roaming,international,usageTime));
        return "Added Successfully";


    }
}
