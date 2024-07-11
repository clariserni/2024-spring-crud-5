package com.customer_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer_product.pojo.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

}
