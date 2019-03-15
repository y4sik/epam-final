package com.web.securedApp.model.domain.customer;

import com.web.securedApp.model.domain.Entity;

public class User extends Entity {

    private String userName;
    private String password;


    public User() {

    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int id, String name, String password) {
        super(id);
        this.userName = name;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: " +
                "id=" + getId() +
                ", userName=" + userName +
                ", password=" + password;
    }
}
