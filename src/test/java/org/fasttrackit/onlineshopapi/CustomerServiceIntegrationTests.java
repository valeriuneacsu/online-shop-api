package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.fasttrackit.onlineshopapi.transfer.customer.CreateCustomerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer_whenValidRequest_thenReturnCreatedCustomer() {

        createCustomer();

    }

    private Customer createCustomer() {
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
