package com.customer_product.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private int price;

    private int vat; //iva percentuale

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;
    
    public Product() {
    }

    
    public Product(String name, int price, int vat) throws Exception{
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public int getFullPrice(boolean vat) {
        int totalPrice = price;
        if (vat) {
            totalPrice += price * (this.vat / 100);
        }
        return totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price < 0){
            throw new IllegalArgumentException("Non si può inserire un prezzo negativo");
        }
        this.price = price;
    }

    public int getVat() {
        return vat;
    }
    
    public void setVat(int vat) {
        if(vat<0){
            throw new IllegalArgumentException("Non si può inserire un numero negativo");
        }
        this.vat = vat;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" + 
                "id=" + id +
                ", name=" + name + 
                ", price=" + price + 
                ", vat=" + vat +  
                '}';
    }

    
}
