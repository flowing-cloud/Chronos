package com.chronos.repository;

import com.chronos.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by crazyStone on 2018-11-06.
 */
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);

    List<Customer> findAllByOrderById();
}
