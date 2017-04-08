package io.spring.batch.services;

import io.spring.batch.domain.Customer;
import io.spring.batch.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hosni on 09/03/2017.
 */
@Service
public class CustomerServiceImp implements CustomerService{


    @Autowired
    private CustomerRepo customerRepo;

    public void save( Customer customer) {

        customerRepo.save(customer);

    }

    @Override
    public List<Customer> findAll() {
       return  customerRepo.findAll();
    }

    @Override
    public List<Customer> findById(Long id) {

            return  customerRepo.findById(id);
    }
}

