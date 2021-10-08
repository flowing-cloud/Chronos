package com.chronos.controller;

import com.chronos.model.Customer;
import com.chronos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @GetMapping("/customer/{id}")
    public Customer getUser(@PathVariable("id") Integer id){
        return customerRepository.findById(id).get();
    }
    @GetMapping("/customer")
    public Customer insertSchedule(Customer aUser){
        return customerRepository.save(aUser);
    }
}

