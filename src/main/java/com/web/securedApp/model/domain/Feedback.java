package com.web.securedApp.model.domain;

import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.product.Product;

import java.time.LocalDate;
import java.time.LocalTime;

public class Feedback extends Entity {

    private String review;
    private LocalDate date;
    private LocalTime time;
    private Customer customerOwner;
    private Product productOwner;

    public Feedback() {
    }

    public Feedback(int id, String review, LocalDate date, LocalTime time, Customer customerOwner) {
        super(id);
        this.review = review;
        this.date = date;
        this.time = time;
        this.customerOwner = customerOwner;
    }

    public Feedback(int id, String review, LocalDate date, LocalTime time, Product productOwner) {
        super(id);
        this.review = review;
        this.date = date;
        this.time = time;
        this.productOwner = productOwner;
    }

    public Feedback(int id, String review, LocalDate date, LocalTime time, Customer customerOwner, Product productOwner) {
        super(id);
        this.review = review;
        this.date = date;
        this.time = time;
        this.customerOwner = customerOwner;
        this.productOwner = productOwner;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Customer getCustomerOwner() {
        return customerOwner;
    }

    public void setCustomerOwner(Customer customerOwner) {
        this.customerOwner = customerOwner;
    }

    public Product getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(Product productOwner) {
        this.productOwner = productOwner;
    }

    @Override
    public String toString() {
        return "Feedback: " +
                "id=" + getId() +
                ", review=" + review +
                ", date=" + date;
    }
}
