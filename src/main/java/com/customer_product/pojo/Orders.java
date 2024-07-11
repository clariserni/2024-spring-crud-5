package com.customer_product.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    private List<Product> products;


    public int getFullPrice(boolean vat){
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getFullPrice(vat);
        }
        return totalPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public void addProuct(Product p){
        products.add(p);
    }

    public void removeProduct(Product prod){
        products = products.stream()
                            .filter(p -> p.getId() != prod.getId())
                            .toList();
    }

    @Override
    public String toString() {

        return "Order {id=" + id +
                "id customer:" + getCustomer().getId()+ 
                 "}";
    }

    
}
