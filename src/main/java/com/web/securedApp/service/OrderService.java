package com.web.securedApp.service;

import com.web.securedApp.model.domain.Order;

public interface OrderService extends PersistentService<Order> {
    boolean deleteOrderproductById(int orderId, int productId);

    int insertOrderproduct(Order order);
}
