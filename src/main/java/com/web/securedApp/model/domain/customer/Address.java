package com.web.securedApp.model.domain.customer;

import com.web.securedApp.model.domain.Entity;
import com.web.securedApp.model.domain.customer.Customer;

public class Address extends Entity {
    private String country;
    private String city;
    private String street;
    private String houseFlat;
    private int postcode;
    private Customer customerOwner;

    public Address() {
    }

    public Address(int id, String country, String city, String street, String houseFlat, int postcode) {
        super(id);
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseFlat = houseFlat;
        this.postcode = postcode;
    }

    public Address(String country, String city, String street, String houseFlat, int postcode, Customer customerOwner) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseFlat = houseFlat;
        this.postcode = postcode;
        this.customerOwner = customerOwner;
    }

    public Address(int id, String country, String city, String street, String houseFlat, int postcode, Customer customerOwner) {
        super(id);
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseFlat = houseFlat;
        this.postcode = postcode;
        this.customerOwner = customerOwner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseFlat() {
        return houseFlat;
    }

    public void setHouseFlat(String houseFlat) {
        this.houseFlat = houseFlat;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public Customer getCustomerOwner() {
        return customerOwner;
    }

    public void setCustomerOwner(Customer customerOwner) {
        this.customerOwner = customerOwner;
    }

    @Override
    public String toString() {
        return "Address: " +
                "id=" + getId() +
                ", country=" + country +
                ", city=" + city +
                ", street=" + street +
                ", houseFlat=" + houseFlat +
                ", postcode=" + postcode;
    }
}
