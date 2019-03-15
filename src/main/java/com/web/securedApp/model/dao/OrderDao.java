package com.web.securedApp.model.dao;

import com.web.securedApp.model.domain.Entity;
import com.web.securedApp.model.domain.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getAll();

    Order getById(int id);

    int insert(Order order);

    boolean delete(int id);

    boolean update(Order order);
}
