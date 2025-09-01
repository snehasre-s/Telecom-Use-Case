package model;

public class Customer {
    private static int idCounter = 1; // Auto-increment counter
    private int customerId;
    String name;
    String phone;
    String email;
    String referral_stat;
    String password;
    int plan_id;
    String family_id;

    private int overageCount;

    public Customer() {
    }

    public Customer(String name, String phone, String email, String referral_stat, String password, int plan_id, String family_id) {
        this.customerId = idCounter++;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.referral_stat = referral_stat;
        this.password = password;
        this.plan_id = plan_id;
        this.family_id = family_id;
        this.overageCount = 0;
    }

    public Customer(String phone , String password) {
        this.phone = phone;
        this.password=password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferral_stat() {
        return referral_stat;
    }

    public void setReferral_stat(String referral_stat) {
        this.referral_stat = referral_stat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }
    public int getOverageCount()
    {
        return overageCount;
    }
    public void setOverageCount(int overageCount)
    {
        this.overageCount = overageCount;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", referral_stat='" + referral_stat + '\'' +
                ", password='" + password + '\'' +
                ", plan_id=" + plan_id +
                ", family_id='" + family_id + '\'' +
                ", overageCount=" + overageCount +
                '}';
    }
}
