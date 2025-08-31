package repo;

import model.Invoice;
import model.Subscription;

import java.time.LocalDate;
import java.util.*;

public class Invoices {

    private static int lastId = 0;
    public static List<Invoice> generatedInvoices = new ArrayList<>();
    public static Map<String, List<Invoice>> familyInvoices = new HashMap<>();

    static {
        addInvoiceInternal(new Invoice(
                ++lastId,
                1,
                1,
                101,
                500.0,
                50.0,
                550.0,
                LocalDate.of(2023, 2, 1),
                false
        ));

        addInvoiceInternal(new Invoice(
                ++lastId,
                2,
                2,
                102,
                700.0,
                150.0,
                850.0,
                LocalDate.of(2023, 2, 1),
                false
        ));

        addInvoiceInternal(new Invoice(
                ++lastId,
                3,
                3,
                103,
                400.0,
                0.0,
                400.0,
                LocalDate.of(2023, 2, 1),
                true
        ));
    }

    // Return all invoices
    public static List<Invoice> getInvoices() {
        return generatedInvoices;
    }

    // Return family invoices grouped by familyId
    public static Map<String, List<Invoice>> getFamilyInvoices() {
        return familyInvoices;
    }

    // Create and add a new invoice programmatically
    public static Invoice createInvoice(int customerId,
                                        int subscriptionId,
                                        int planId,
                                        double baseRental,
                                        double overageAmount,
                                        double totalAmount,
                                        LocalDate invoiceDate,
                                        boolean paid,
                                        String familyId) {

        Invoice invoice = new Invoice(
                ++lastId,
                customerId,
                subscriptionId,
                planId,
                baseRental,
                overageAmount,
                totalAmount,
                invoiceDate,
                paid
        );
        addInvoiceInternal(invoice, familyId);
        return invoice;
    }

    // Internal helper: add invoice into both list and family map
    private static void addInvoiceInternal(Invoice invoice) {
        addInvoiceInternal(invoice, null);
    }

    private static void addInvoiceInternal(Invoice invoice, String familyId) {
        generatedInvoices.add(invoice);

        if (familyId != null) {
            familyInvoices
                    .computeIfAbsent(familyId, k -> new ArrayList<>())
                    .add(invoice);
        }
    }
}
