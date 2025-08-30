package model;

import java.time.LocalDate;

public class Subscription {
    private int subscriptionId;
    private int customerId;
    private int planId;
    private String familyId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean mnpPending;

    public Subscription(int subscriptionId, int customerId, int planId,
                        String familyId, LocalDate startDate, LocalDate endDate,
                        boolean mnpPending) {
        this.subscriptionId = subscriptionId;
        this.customerId = customerId;
        this.planId = planId;
        this.familyId = familyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mnpPending = mnpPending;
    }

    public int getSubscriptionId() { return subscriptionId; }
    public int getCustomerId() { return customerId; }
    public int getPlanId() { return planId; }
    public String getFamilyId() { return familyId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public boolean isMnpPending() { return mnpPending; }

    @Override
    public String toString() {
        return "Subscription{id=" + subscriptionId + ", customerId=" + customerId +
                ", planId=" + planId + ", startDate=" + startDate + ", endDate=" + endDate +
                ", mnpPending=" + mnpPending + "}";
    }
}

