package services;

import dao.InvoiceDAO;
import dao.InvoiceDAOImpl;
import model.Invoice;

public class InvoiceServiceImpl implements InvoiceService{
    @Override
    public Invoice displayInvoice(int customerId) {
        InvoiceDAO invoiceDAOObj = new InvoiceDAOImpl();
        try{
            return invoiceDAOObj.displayInvoice(customerId);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
