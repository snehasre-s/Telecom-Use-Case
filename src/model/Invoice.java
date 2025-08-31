package model;

import java.time.LocalDate;

public class Invoice {
    private int invoiceId;
    private int  customerId;
    private int subscriptionId;
    private int planId;
    private double baseRental;
    private double overageAmount;
    private double totalAmount;
    private LocalDate invoiceDate;
    private boolean paid;

    public Invoice(int invoiceId, int customerId, int subscriptionId, int planId,
                   double baseRental, double overageAmount, double totalAmount,
                   LocalDate invoiceDate, boolean paid) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.subscriptionId = subscriptionId;
        this.planId = planId;
        this.baseRental = baseRental;
        this.overageAmount = overageAmount;
        this.totalAmount = totalAmount;
        this.invoiceDate = invoiceDate;
        this.paid = paid;
    }

    public int getInvoiceId() { return invoiceId; }
    public int getSubscriptionId() { return subscriptionId; }
    public int getPlanId() { return planId; }
    public double getBaseRental() { return baseRental; }
    public double getOverageAmount() { return overageAmount; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
    public boolean isPaid() { return paid; }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setBaseRental(double baseRental) {
        this.baseRental = baseRental;
    }

    public void setOverageAmount(double overageAmount) {
        this.overageAmount = overageAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Invoice{id=" + invoiceId + ", subscriptionId=" + subscriptionId +
                ", planId=" + planId + ", baseRental=" + baseRental +
                ", overage=" + overageAmount + ", total=" + totalAmount +
                ", invoiceDate=" + invoiceDate + ", paid=" + paid + "}";
    }
}

