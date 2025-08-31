package services;

import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import model.Customer;
import model.Invoice;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private CustomerDAO customerDAO=new CustomerDAOImpl();
    @Override
    public void registerCustomer(Customer customer) {
    customerDAO.registerCustomer(customer);
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
