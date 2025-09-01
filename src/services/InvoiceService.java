package services;

import model.Invoice;
import model.Subscription;

import java.util.List;

public interface InvoiceService {
    List<Invoice> displayInvoice(int customerId);
    Invoice generateNewInvoice(int customerId);
}
