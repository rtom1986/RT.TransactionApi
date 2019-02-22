package api.services;

import api.models.Customer;

public interface CustomerService {

    Customer getCustomer(int customerId);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(int customerId, Customer customer);

    void deleteCustomer(int customerId);
}
