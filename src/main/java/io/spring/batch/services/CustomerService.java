package io.spring.batch.services;

import io.spring.batch.domain.Customer;

import java.util.List;

/**
 * Created by Hosni on 09/03/2017.
 */
public interface CustomerService {

    public void save(Customer customer);

    public List<Customer> findAll();

    public List<Customer> findById(Long id);

}
