package model;

public class Plan {
    private int id;
    private String name;
    private double monthlyRental;

    private double dataAllowanceGb;
    private int voiceAllowanceMin;
    private int smsAllowance;

    private double dataOverageRate;
    private double voiceOverageRate;
    private double smsOverageRate;

    private double fupLimitGb; // Fair Usage Policy Limit
    private boolean rolloverEnabled;
    private double roamingChargePerGb;
    private double roamingChargePerMin;

    private boolean familyShared;
    private double familyDataPoolGb;

    private boolean weekendFreeVoice;

    public Plan() {
    }

    public Plan(int id, String name, double monthlyRental, double dataAllowanceGb, int voiceAllowanceMin, int smsAllowance, double dataOverageRate, double voiceOverageRate, double smsOverageRate, double fupLimitGb, boolean rolloverEnabled, double roamingChargePerGb, double roamingChargePerMin, boolean familyShared, double familyDataPoolGb, boolean weekendFreeVoice) {
        this.id = id;
        this.name = name;
        this.monthlyRental = monthlyRental;
        this.dataAllowanceGb = dataAllowanceGb;
        this.voiceAllowanceMin = voiceAllowanceMin;
        this.smsAllowance = smsAllowance;
        this.dataOverageRate = dataOverageRate;
        this.voiceOverageRate = voiceOverageRate;
        this.smsOverageRate = smsOverageRate;
        this.fupLimitGb = fupLimitGb;
        this.rolloverEnabled = rolloverEnabled;
        this.roamingChargePerGb = roamingChargePerGb;
        this.roamingChargePerMin = roamingChargePerMin;
        this.familyShared = familyShared;
        this.familyDataPoolGb = familyDataPoolGb;
        this.weekendFreeVoice = weekendFreeVoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public double getDataAllowanceGb() {
        return dataAllowanceGb;
    }

    public void setDataAllowanceGb(double dataAllowanceGb) {
        this.dataAllowanceGb = dataAllowanceGb;
    }

    public int getVoiceAllowanceMin() {
        return voiceAllowanceMin;
    }

    public void setVoiceAllowanceMin(int voiceAllowanceMin) {
        this.voiceAllowanceMin = voiceAllowanceMin;
    }

    public int getSmsAllowance() {
        return smsAllowance;
    }

    public void setSmsAllowance(int smsAllowance) {
        this.smsAllowance = smsAllowance;
    }

    public double getDataOverageRate() {
        return dataOverageRate;
    }

    public void setDataOverageRate(double dataOverageRate) {
        this.dataOverageRate = dataOverageRate;
    }

    public double getVoiceOverageRate() {
        return voiceOverageRate;
    }

    public void setVoiceOverageRate(double voiceOverageRate) {
        this.voiceOverageRate = voiceOverageRate;
    }

    public double getSmsOverageRate() {
        return smsOverageRate;
    }

    public void setSmsOverageRate(double smsOverageRate) {
        this.smsOverageRate = smsOverageRate;
    }

    public double getFupLimitGb() {
        return fupLimitGb;
    }

    public void setFupLimitGb(double fupLimitGb) {
        this.fupLimitGb = fupLimitGb;
    }

    public boolean isRolloverEnabled() {
        return rolloverEnabled;
    }

    public void setRolloverEnabled(boolean rolloverEnabled) {
        this.rolloverEnabled = rolloverEnabled;
    }

    public double getRoamingChargePerGb() {
        return roamingChargePerGb;
    }

    public void setRoamingChargePerGb(double roamingChargePerGb) {
        this.roamingChargePerGb = roamingChargePerGb;
    }

    public double getRoamingChargePerMin() {
        return roamingChargePerMin;
    }

    public void setRoamingChargePerMin(double roamingChargePerMin) {
        this.roamingChargePerMin = roamingChargePerMin;
    }

    public boolean isFamilyShared() {
        return familyShared;
    }

    public void setFamilyShared(boolean familyShared) {
        this.familyShared = familyShared;
    }

    public double getFamilyDataPoolGb() {
        return familyDataPoolGb;
    }

    public void setFamilyDataPoolGb(double familyDataPoolGb) {
        this.familyDataPoolGb = familyDataPoolGb;
    }

    public boolean isWeekendFreeVoice() {
        return weekendFreeVoice;
    }

    public void setWeekendFreeVoice(boolean weekendFreeVoice) {
        this.weekendFreeVoice = weekendFreeVoice;
    }


    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monthlyRental=" + monthlyRental +
                ", dataAllowanceGb=" + dataAllowanceGb +
                ", voiceAllowanceMin=" + voiceAllowanceMin +
                ", smsAllowance=" + smsAllowance +
                ", dataOverageRate=" + dataOverageRate +
                ", voiceOverageRate=" + voiceOverageRate +
                ", smsOverageRate=" + smsOverageRate +
                ", fupLimitGb=" + fupLimitGb +
                ", rolloverEnabled=" + rolloverEnabled +
                ", roamingChargePerGb=" + roamingChargePerGb +
                ", roamingChargePerMin=" + roamingChargePerMin +
                ", familyShared=" + familyShared +
                ", familyDataPoolGb=" + familyDataPoolGb +
                ", weekendFreeVoice=" + weekendFreeVoice +
                '}';
    }
}
