package com.customer_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer_product.pojo.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
