package com.web.securedApp.model.domain;

import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.product.Product;
import com.web.securedApp.model.constant.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order extends Entity {
    private LocalDate date;
    private OrderStatus status;
    private double price;
    private Customer customerOwner;
    private ArrayList<Product> products;

    public Order() {
    }

    public Order(LocalDate date, OrderStatus status, double price, Customer customerOwner, ArrayList<Product> products) {
        this.date = date;
        this.status = status;
        this.price = price;
        this.customerOwner = customerOwner;
        this.products = products;
    }

    public Order(int id, LocalDate date, OrderStatus status, double price, Customer customerOwner) {
        super(id);
        this.date = date;
        this.status = status;
        this.price = price;
        this.customerOwner = customerOwner;
    }

    public Order(int id, LocalDate date, OrderStatus status, double price, ArrayList<Product> products) {
        super(id);
        this.date = date;
        this.status = status;
        this.price = price;
        this.products = products;
    }

    public Order(int id, LocalDate date, OrderStatus status, double price, Customer customerOwner,
                 ArrayList<Product> products) {
        super(id);
        this.date = date;
        this.status = status;
        this.price = price;
        this.customerOwner = customerOwner;
        this.products = products;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomerOwner() {
        return customerOwner;
    }

    public void setCustomerOwner(Customer customerOwner) {
        this.customerOwner = customerOwner;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order: " +
                "id=" + getId() +
                ", date=" + date +
                ", status=" + status +
                ", price=" + price;
    }
}
