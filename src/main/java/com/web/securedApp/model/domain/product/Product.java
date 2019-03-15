package com.web.securedApp.model.domain.product;

import com.web.securedApp.model.domain.Entity;
import com.web.securedApp.model.domain.Feedback;
import com.web.securedApp.model.domain.Order;

import java.util.ArrayList;

public class Product extends Entity {

    private String name;
    private int cost;
    private int quantity;
    private String description;
    private ArrayList<Picture> pictures;
    private ArrayList<Order> orders;
    private ArrayList<Feedback> feedbacks;
    private Category category;

    public Product() {

    }

    public Product(int id, String name, int cost, int quantity, String description, Category category) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }

    public Product(String name, int cost, int quantity, String description, ArrayList<Picture> pictures,
                   ArrayList<Order> orders, ArrayList<Feedback> feedbacks, Category category) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.description = description;
        this.pictures = pictures;
        this.orders = orders;
        this.feedbacks = feedbacks;
        this.category = category;
    }

    public Product(int id, String name, int cost, int quantity, String description, ArrayList<Picture> pictures,
                   ArrayList<Order> orders, ArrayList<Feedback> feedbacks, Category category) {
        super(id);
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.description = description;
        this.pictures = pictures;
        this.orders = orders;
        this.feedbacks = feedbacks;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product: " +
                "id=" + getId() +
                ", name=" + name +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", category=" + category +
                ", description=" + description;
    }
}
