package repo;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSeeder {
    private static List<Customer> customers;
    public static List<Customer> seedCustomers() {
        if (customers == null) {
            customers = new ArrayList<>();
            customers.add(new Customer(
                    "John Doe",
                    "9876543210",
                    "john@example.com",
                    "REFERRED",
                    "pass123",
                    1,
                    null
            ));
            customers.get(customers.size() - 1).setOverageCount(1); // John had 1 overage

            customers.add(new Customer(
                    "Jane Smith",
                    "9123456789",
                    "jane@example.com",
                    null,
                    "pass456",
                    2,
                    "FAMILY001"
            ));
            customers.get(customers.size() - 1).setOverageCount(3); // Jane had 3 overages

            customers.add(new Customer(
                    "Amit Sharma",
                    "9988776655",
                    "amit@example.com",
                    "REFERRED",
                    "amit789",
                    3,
                    "FAMILY001"
            ));
            customers.get(customers.size() - 1).setOverageCount(0); // Amit had no overages
        }
        return customers;
    }
}


