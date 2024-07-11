package com.customer_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer_product.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
