package dao;



import model.Customer;
import model.Usage;
import model.Invoice;
import java.util.List;

public interface CustomerDAO {

    void registerCustomer(Customer customer);

    List<Customer> listAllCustomers();

    List<Invoice> displayInvoice(int customerId);

    void addUsage(int customerId, double data, int voice, int sms);

    void updateCustomerDetails(int customerId, String field, String newValue);
}

