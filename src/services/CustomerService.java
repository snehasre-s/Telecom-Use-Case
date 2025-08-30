package services;



import model.Customer;
import model.Invoice;
import java.util.List;

public interface CustomerService {

    void registerCustomer(Customer customer);

    List<Customer> listAllCustomers();

    Invoice displayInvoice(int customerId);

    void addUsage(int customerId, double data, int voice, int sms);

    void updateCustomerDetails(int customerId, String field, String newValue);
}


