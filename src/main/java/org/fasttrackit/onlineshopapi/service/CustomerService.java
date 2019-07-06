package org.fasttrackit.onlineshopapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.repository.CustomerRepository;
import org.fasttrackit.onlineshopapi.transfer.customer.CreateCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CustomerService.class);

    //IoC (inversion of control) and dependency injection
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }


    public Customer createCustomer(CreateCustomerRequest request){

        LOGGER.info("Creating customer {}",request );  // in loc de .info se poate pune .debug, .error

        Customer customer = objectMapper.convertValue(request, Customer.class);

        return customerRepository.save(customer);

    }


    public Customer getCustomer(long id) throws ResourceNotFoundException {
        LOGGER.info("Retreiving customer {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer " + id + " does not exist."));

    }






}
