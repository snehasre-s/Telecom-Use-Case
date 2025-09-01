package dao;

import model.Invoice;

import java.util.List;

public interface InvoiceDAO {
    List<Invoice> displayInvoice(int customerId);
    Invoice generateNewInvoice(int customerId);
}
