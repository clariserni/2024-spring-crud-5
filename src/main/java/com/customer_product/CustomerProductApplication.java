package com.customer_product;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.customer_product.pojo.Customer;
import com.customer_product.pojo.Orders;
import com.customer_product.pojo.Product;
import com.customer_product.service.Customerservice;
import com.customer_product.service.OrderService;
import com.customer_product.service.ProductService;

@SpringBootApplication
public class CustomerProductApplication implements CommandLineRunner{

	@Autowired
	ProductService productService;

	@Autowired
	Customerservice customerservice;

	@Autowired
	OrderService orderService;


	public static void main(String[] args) {
		SpringApplication.run(CustomerProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Customer c1 = new Customer("Customer1", "Lastname1", "customer1@example.com", "1234567890");
		Customer c2 = new Customer("Customer2", "Lastname2", "customer2@example.com", "9876543210");
		Customer c3 = new Customer("Customer3", "Lastname3", "customer3@example.com", "5678901234");

		customerservice.save(c1);
		customerservice.save(c2);
		customerservice.save(c3);
		
		Product p1 = new Product("Product1", 100, 22);
		Product p2 = new Product("Product2", 50, 22);  
		Product p3 = new Product("Product3", 200, 22);
		Product p4 = new Product("Product4", 75, 22);  
		Product p5 = new Product("Product5", 120, 22); 

		productService.save(p1);
		productService.save(p2);
		productService.save(p3);
		productService.save(p4);
		productService.save(p5);

		Orders o1 = new Orders();
		o1.setCustomer(c1);
		Orders o2 = new Orders();
		o2.setCustomer(c3);
		Orders o3 = new Orders();
		o3.setCustomer(c2);
		Orders o4 = new Orders();
		o4.setCustomer(c3);

		orderService.save(o1);
		orderService.save(o2);
		orderService.save(o3);
		orderService.save(o4);

		List<Orders> orders = orderService.getAllOrders();
		List<Product> products = productService.getAllProducts();
		List<Customer> customers = customerservice.getAllCustomers();

		orders.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		products.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		customers.forEach(System.out::println);

		System.out.println("-------------------------------------------------------");

		List<Orders> orders2 = orderService.findAllWithProducts();
		
		o1 = orders2.get(0);
		o2 = orders2.get(1);
		o3 = orders2.get(2);

		o1.addProuct(p5);
		o1.addProuct(p4);
		o3.addProuct(p5);
		o2.addProuct(p3);

		orderService.save(o1);
		orderService.save(o2);
		orderService.save(o3);

		List<Orders> orders3 = orderService.findAllWithProducts();
        
        for (Orders order : orders3) {
            System.out.println("Order ID: " + order.getId());
            for (Product product : order.getProducts()) {
                System.out.println("  Product ID: " + product.getId() + ", Name: " + product.getName());
            }
            System.out.println();
        }

		
	}

}
