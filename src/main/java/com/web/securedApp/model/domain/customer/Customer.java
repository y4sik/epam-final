package com.web.securedApp.model.domain.customer;

import com.web.securedApp.model.domain.Entity;
import com.web.securedApp.model.domain.Feedback;
import com.web.securedApp.model.domain.Order;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Entity {

    private String name;
    private String surname;
    private String gender;
    private LocalDate dateOfBirthday;
    private String email;
    private String phoneNumber;
    private ArrayList<Address> addresses;
    private ArrayList<Order> orders;
    private ArrayList<Feedback> feedbacks;

    public Customer() {

    }

    public Customer(String name, String surname, String gender, LocalDate dateOfBirthday, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String surname, String gender, LocalDate dateOfBirthday, String email, String phoneNumber, ArrayList<Address> addresses, ArrayList<Order> orders, ArrayList<Feedback> feedbacks) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.orders = orders;
        this.feedbacks = feedbacks;
    }

    public Customer(int id, String name, String surname, String gender, LocalDate dateOfBirthday, String email, String phoneNumber, ArrayList<Address> addresses, ArrayList<Order> orders, ArrayList<Feedback> feedbacks) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.orders = orders;
        this.feedbacks = feedbacks;
    }

    public Customer(int id, String name, String surname, String gender, LocalDate dateOfBirthday, String email, String phoneNumber) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
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

    @Override
    public String toString() {
        return "Customer: " +
                "id=" + getId() +
                ", name=" + name +
                ", surname=" + surname +
                ", dateOfBirthday=" + dateOfBirthday +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber;
    }
}
