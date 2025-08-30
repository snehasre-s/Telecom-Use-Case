package model;

import java.time.LocalDate;

public class Invoice {
    private int invoiceId;
    private int subscriptionId;
    private int planId;
    private double baseRental;
    private double overageAmount;
    private double totalAmount;
    private LocalDate invoiceDate;
    private boolean paid;

    public Invoice(int invoiceId, int subscriptionId, int planId,
                   double baseRental, double overageAmount, double totalAmount,
                   LocalDate invoiceDate, boolean paid) {
        this.invoiceId = invoiceId;
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

    @Override
    public String toString() {
        return "Invoice{id=" + invoiceId + ", subscriptionId=" + subscriptionId +
                ", planId=" + planId + ", baseRental=" + baseRental +
                ", overage=" + overageAmount + ", total=" + totalAmount +
                ", invoiceDate=" + invoiceDate + ", paid=" + paid + "}";
    }
}

