package dao;

import model.Customer;
import model.Invoice;
import repo.CustomerSeeder;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private List<Customer> customers;

    public CustomerDAOImpl() {
        this.customers = CustomerSeeder.seedCustomers();
    }

    @Override
    public void registerCustomer(Customer customer) {

        customers.add(customer);

    }

    @Override
    public List<Customer> listAllCustomers() {
        return customers;
    }

    @Override
    public Invoice displayInvoice(int customerId) {
        InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
        return invoiceDAO.displayInvoice(customerId);
    }

    @Override
    public void addUsage(int customerId, double data, int voice, int sms) {
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                c.setOverageCount(c.getOverageCount() + 1);
                break;
            }
        }
    }

    @Override
    public void updateCustomerDetails(int customerId, String field, String newValue) {
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                switch (field.toLowerCase()) {
                    case "name":
                        c.setName(newValue);
                        break;
                    case "phone":
                        c.setPhone(newValue);
                        break;
                    case "email":
                        c.setEmail(newValue);
                        break;
                    case "referral_stat":
                        c.setReferral_stat(newValue);
                        break;
                    case "password":
                        c.setPassword(newValue);
                        break;
                    case "plan_id":
                        c.setPlan_id(Integer.parseInt(newValue));
                        break;
                    case "family_id":
                        c.setFamily_id(newValue);
                        break;
                }
                break;
            }
        }
    }
}
