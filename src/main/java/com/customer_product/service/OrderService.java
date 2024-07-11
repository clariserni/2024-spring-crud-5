package com.customer_product.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer_product.pojo.Orders;
import com.customer_product.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    //read
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public List<Orders> findAllWithProducts(){
        List<Orders> orders = orderRepository.findAll();

        for(Orders o : orders){
            Hibernate.initialize(o.getProducts());
        }

        return orders;
    }

    @Transactional
    public Optional<Orders> findByIdWithProducts(int id){

        Optional<Orders> orders = getOrderById(id);
        
        if(orders.isEmpty()){
            return Optional.empty();
        }

        Hibernate.initialize(orders.get().getProducts());

        return orders;
    }

    //save e update
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    //delete
    public void deleteOrder(Orders order) {
        orderRepository.delete(order);
    }
}
