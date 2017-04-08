package io.spring.batch.repositories;

import io.spring.batch.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hosni on 09/03/2017.
 */



@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    List<Customer> findById(Long id);

}
