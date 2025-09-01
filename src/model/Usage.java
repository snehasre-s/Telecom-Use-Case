package model;

import java.time.LocalDateTime;

public class Usage {
    int customerID;
    int subscriptionID;
    private double dataGb;
    private int voiceMinutes;
    private int smsCount;
    private boolean roaming;
    private boolean international;
    private LocalDateTime usageTime;

    public Usage() {
    }

    public Usage(int customerID,int subscriptionID,double dataGb, int voiceMinutes, int smsCount, boolean roaming, boolean international, LocalDateTime usageTime) {
        this.customerID=customerID;
        this.subscriptionID=subscriptionID;
        this.dataGb = dataGb;
        this.voiceMinutes = voiceMinutes;
        this.smsCount = smsCount;
        this.roaming = roaming;
        this.international = international;
        this.usageTime = usageTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public double getDataGb() {
        return dataGb;
    }

    public void setDataGb(double dataGb) {
        this.dataGb = dataGb;
    }

    public int getVoiceMinutes() {
        return voiceMinutes;
    }

    public void setVoiceMinutes(int voiceMinutes) {
        this.voiceMinutes = voiceMinutes;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public boolean isRoaming() {
        return roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public LocalDateTime getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(LocalDateTime usageTime) {
        this.usageTime = usageTime;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "customerID=" + customerID +
                ", subscriptionID=" + subscriptionID +
                ", dataGb=" + dataGb +
                ", voiceMinutes=" + voiceMinutes +
                ", smsCount=" + smsCount +
                ", roaming=" + roaming +
                ", international=" + international +
                ", usageTime=" + usageTime +
                '}';
    }



    public boolean isNightTime(){
        int hour= usageTime.getHour();
        return hour > 0 && hour <= 6;
    }
    public boolean isWeekend(){
        int day=usageTime.getDayOfWeek().getValue();
        return day==6||day==7;

    }
}
