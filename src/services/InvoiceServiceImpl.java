package services;

import dao.InvoiceDAO;
import dao.InvoiceDAOImpl;
import model.Invoice;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService{
    @Override
    public List<Invoice> displayInvoice(int customerId) {
        InvoiceDAO invoiceDAOObj = new InvoiceDAOImpl();
        try{
            return invoiceDAOObj.displayInvoice(customerId);
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
            return null; //TODO DO NOT RETURN NULL ONLY RETURN EMPTY OBJECT
            //TODO ALSO RETURN OPTIONAL
        }
    }

    @Override
    public Invoice generateNewInvoice(int customerId) {
        InvoiceDAO invoiceDAOObj = new InvoiceDAOImpl();
        return invoiceDAOObj.generateNewInvoice(customerId);
    }
}
