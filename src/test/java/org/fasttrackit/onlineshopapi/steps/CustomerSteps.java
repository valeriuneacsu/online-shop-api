package org.fasttrackit.onlineshopapi.steps;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.fasttrackit.onlineshopapi.transfer.customer.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;


@Component
public class CustomerSteps {

    @Autowired
    private CustomerService customerService;

    public Customer createCustomer() {

        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setFirstName("Dorel");
        request.setLastName("Tarnacop");
        request.setAge(100);

        Customer createdCustomer = customerService.createCustomer(request);

        assertThat (createdCustomer, notNullValue());
        assertThat (createdCustomer.getId(), greaterThan(0L));
        assertThat (createdCustomer.getFirstName(), is(request.getFirstName()));
        assertThat (createdCustomer.getLastName(),is(request.getLastName()));
        assertThat (createdCustomer.getAge(), is(request.getAge()));

        return createdCustomer;

    }

}
