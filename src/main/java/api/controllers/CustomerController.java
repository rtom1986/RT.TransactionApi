package api.controllers;

import api.models.Customer;
import api.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    /**
     * Customer service implementation
     */
    private CustomerService customerService;

    /**
     * Constructor
     *
     * @param customerService The customer service implementation
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Get customer
     *
     * @param id The customer Id to be fetched.
     * @return The customer entity fetched from persistent storage.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        try {
            var customerEntity = customerService.getCustomer(id);
            if (customerEntity != null) {
                return new ResponseEntity<>(customerEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create customer
     *
     * @param customerDto The customer entity to be persisted.
     * @return The newly persisted customer entity.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customerDto) {
        try {
            return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update customer
     *
     * @param id The customer Id to be updated.
     * @param customerDto The updated customer entity to be persisted.
     * @return The newly persisted customer entity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @Valid @RequestBody Customer customerDto) {
        try {
            var customerEntity = customerService.updateCustomer(id, customerDto);
            if (customerEntity != null) {
                return new ResponseEntity<>(customerEntity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete customer
     *
     * @param id The customer Id to be deleted.
     * @return No Content if the customer was deleted.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCustomer(@PathVariable int id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
