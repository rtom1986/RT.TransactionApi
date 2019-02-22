package api.services;

import api.models.Customer;
import api.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * The customer repository implementation
     */
    private CustomerRepository customerRepo;

    /**
     * Constructor
     *
     * @param customerRepo The customer repository implementation
     */
    public CustomerServiceImpl(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    /**
     * Get customer
     *
     * @param customerId The customer Id to be fetched.
     * @return The customer entity fetched from persistent storage.
     */
    @Override
    public Customer getCustomer(int customerId) {
        var customer = customerRepo.findById(customerId);
        return customer.orElse(null);
    }

    /**
     * Create customer
     *
     * @param customerDto The customer entity to be persisted.
     * @return The newly persisted customer entity.
     */
    @Override
    public Customer createCustomer(Customer customerDto) {
        return customerRepo.save(customerDto);
    }

    /**
     * Update customer
     *
     * @param customerId The customer Id to be updated.
     * @param customerDto The updated customer entity to be persisted.
     * @return The newly updated customer entity.
     */
    @Override
    public Customer updateCustomer(int customerId, Customer customerDto) {
        var customerRecord = customerRepo.findById(customerId);
        if (customerRecord.isPresent()) {
            var cust = customerRecord.get();
            cust.setFirstName(customerDto.getFirstName());
            cust.setLastName(customerDto.getLastName());
            cust.setEmailAddress(customerDto.getEmailAddress());
            cust.setPhoneNumber(customerDto.getPhoneNumber());
            customerRepo.save(cust);
            return cust;
        } else {
            return null;
        }
    }

    /**
     * Delete customer
     *
     * @param customerId The customer Id to be deleted.
     */
    @Override
    public void deleteCustomer(int customerId) {
        var customer = customerRepo.findById(customerId);
        customer.ifPresent(x -> customerRepo.delete(x));
    }
}
