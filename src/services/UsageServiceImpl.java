package services;

import dao.UsageDAO;
import dao.UsageDAOImpl;
import model.Usage;

import java.time.LocalDateTime;
import java.util.List;

public class UsageServiceImpl implements UsageService{
    UsageDAO usageDAO=new UsageDAOImpl();
    @Override
    public List<Usage> getUsageByCustomerId(int customerId) {
        return usageDAO.getUsageByCustomerId(customerId);
    }

    @Override
    public List<Usage> getAllUsages() {
        return usageDAO.getAllUsages();
    }

    @Override
    public String addUsage(int customerID,int subscriptionID,double dataGb, int voiceMinutes, int smsCount, boolean roaming, boolean international, LocalDateTime usageTime) {
        return usageDAO.addUsage(customerID,subscriptionID,dataGb,voiceMinutes,smsCount,roaming,international,usageTime);
    }
}
