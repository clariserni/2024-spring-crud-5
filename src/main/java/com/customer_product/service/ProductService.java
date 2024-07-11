package com.customer_product.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer_product.pojo.Product;

import com.customer_product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //read
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

     @Transactional
    public List<Product> findAllWithOrders(){
        List<Product> products = productRepository.findAll();

        for(Product p : products){
            Hibernate.initialize(p.getOrders());
        }

        return products;
    }

    @Transactional
    public Optional<Product> findByIdWithOrders(int id){

        Optional<Product> products = getProductById(id);
        
        if(products.isEmpty()){
            return Optional.empty();
        }

        Hibernate.initialize(products.get().getOrders());

        return products;
    }

    //save e update
    public Product save(Product product) {
        return productRepository.save(product);
    }

    //delete
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
