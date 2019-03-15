package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.OrderDao;
import com.web.securedApp.model.dao.OrderproductDao;
import com.web.securedApp.model.dao.impl.OrderDaoImpl;
import com.web.securedApp.model.dao.impl.OrderproductDaoImpl;
import com.web.securedApp.model.domain.Order;
import com.web.securedApp.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderproductDao orderproductDao = new OrderproductDaoImpl();

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderDao.getAll();
        if (orders.isEmpty()) {
            LOGGER.info("List orders is null!");
            return null;
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        if (id < 0) {
            return null;
        }
        Order order = orderDao.getById(id);
        if (order == null) {
            LOGGER.info("Order is null!");
        }
        return order;
    }

    @Override
    public int insert(Order order) {
        if (order == null) {
            return -1;
        }
        int id = orderDao.insert(order);
        if (id == -1) {
            LOGGER.error("Failed to insert order!");
        }
        return id;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = orderDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete order by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Order order) {
        if (order == null) {
            return false;
        }
        boolean checkUpdate = orderDao.update(order);
        if (!checkUpdate) {
            LOGGER.info("Failed to update order");
        }
        return checkUpdate;
    }

    @Override
    public boolean deleteOrderproductById(int orderId, int productId) {
        if (orderId < 0 || productId < 0) {
            return false;
        }
        boolean checkDelete = orderproductDao.deleteById(orderId, productId);
        if (!checkDelete) {
            LOGGER.info("Failed to delete orderproduct");
        }
        return checkDelete;
    }

    @Override
    public int insertOrderproduct(Order order) {
        if (order == null) {
            return -1;
        }
        int id = orderproductDao.insert(order);
        if (id == -1) {
            LOGGER.error("Failed to insert orderproduct!");
        }
        return id;
    }
}
