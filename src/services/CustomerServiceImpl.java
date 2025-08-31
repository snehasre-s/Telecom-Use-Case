package services;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import model.Customer;
import model.Invoice;
import model.Subscription;

import java.time.LocalDate;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private CustomerDAO customerDAO=new CustomerDAOImpl();
    private SubscriptionService subscriptionService = new SubscriptionServiceImpl();


    @Override
    public void registerCustomer(Customer customer) {
        customerDAO.registerCustomer(customer);
        if (customer.getPlan_id() != 0) {
            Subscription subscription = new Subscription();
            subscription.setCustomerId(customer.getCustomerId());
            subscription.setPlanId(customer.getPlan_id());
            subscription.setStartDate(LocalDate.now());
            subscriptionService.createSubscription(subscription);
        }
    }

    @Override
    public List<Customer> listAllCustomers() {
        return customerDAO.listAllCustomers();
    }

    @Override
    public Invoice displayInvoice(int customerId) {
        return customerDAO.displayInvoice(customerId);
    }

    @Override
    public void addUsage(int customerId, double data, int voice, int sms) {
        customerDAO.addUsage(customerId,data,voice,sms);
    }

    @Override
    public void updateCustomerDetails(int customerId, String field, String newValue) {
    customerDAO.updateCustomerDetails(customerId,field,newValue);
    }
}
