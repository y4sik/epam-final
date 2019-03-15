package com.web.securedApp.model.domain.product;

import com.web.securedApp.model.domain.Entity;

public class Category extends Entity {
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category: " +
                "name=" + name;
    }
}
