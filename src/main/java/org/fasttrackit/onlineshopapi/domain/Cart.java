package org.fasttrackit.onlineshopapi.domain;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)  //.EAGER in caz ca vreau detalii despre customer (FirstName, LastName)
    @MapsId
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
