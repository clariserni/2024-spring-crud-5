package com.customer_product.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer_product.pojo.Customer;

import com.customer_product.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class Customerservice {

    @Autowired
    CustomerRepository customerRepository;

    //read
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

     @Transactional
    public List<Customer> findAllWithOrders(){
        List<Customer> customers = customerRepository.findAll();

        for(Customer c : customers){
            Hibernate.initialize(c.getOrders());
        }

        return customers;
    }

    @Transactional
    public Optional<Customer> findByIdWithOrders(int id){

        Optional<Customer> customer = getCustomerById(id);
        
        if(customer.isEmpty()){
            return Optional.empty();
        }

        Hibernate.initialize(customer.get().getOrders());

        return customer;
    }
    //save
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    //delete
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
}
