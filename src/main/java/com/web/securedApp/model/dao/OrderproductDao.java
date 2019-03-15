package com.web.securedApp.model.dao;

import com.web.securedApp.model.domain.Order;

public interface OrderproductDao {
    boolean deleteById(int orderId, int productId);
    int insert(Order order);
}
