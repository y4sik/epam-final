package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.constant.OrderStatus;
import com.web.securedApp.model.dao.OrderDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.Order;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.product.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    //    private static final String SQL_GET_ALL_ORDER = "select * from `order`";
    private static final String SQL_GET_ALL_ORDER_WITH_PRODUCT_ID_BY_ID = "select `order`.*, orderproduct.product_id," +
            " orderproduct.product_quantity from `order` left join orderproduct on `order`.id = orderproduct.order_id";
    //    private static final String SQL_GET_ORDER_BY_ID = "select * from `order` where id=?";
    private static final String SQL_GET_ORDER_WITH_PRODUCT_ID_BY_ID = "select `order`.*, orderproduct.product_id," +
            " orderproduct.product_quantity from `order` left join orderproduct on `order`.id = orderproduct.order_id " +
            "where `order`.id=?";
    private static final String SQL_INSERT_ORDER = "insert into `order` (customer_id, date, status, price)" +
            " values (?,?,?,?)";
    private static final String SQL_INSERT_ORDERPRODUCT = "insert into orderproduct(order_id, product_id, " +
            "product_quantity) values(?,?,?)";
    private static final String SQL_DELETE_ORDER = "delete from order where customer_id=? and date=? " +
            "and status=? and price=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "delete from `order` where id=?";
    private static final String SQL_UPDATE_ORDER = "update `order` set date=?, status=?, price=? where id=?";


    public List<Order> getAll() {//need to test
        Map<Integer, Order> orderMap = new HashMap<>();
        Map<Integer, Product> productMap = new HashMap<>();
        List<Order> orders = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_GET_ALL_ORDER_WITH_PRODUCT_ID_BY_ID);
            orders = new ArrayList<>();
            LOGGER.info("Take order with products and customer");
            int k = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                if (!orderMap.containsKey(id)) {
                    int customerId = resultSet.getInt(2);
                    LocalDate date = resultSet.getDate(3).toLocalDate();
                    OrderStatus status = OrderStatus.valueOf(resultSet.getString(4).toUpperCase());
                    double price = resultSet.getDouble(5);
                    LOGGER.trace("Cycle[" + k + "] in getAllOrders, id=" + id + ", " + ", date=" + date +
                            ", status=" + status + ", price=" + price);
                    Customer customer = new Customer();
                    customer.setId(customerId);
                    Order order = new Order(id, date, status, price, customer, new ArrayList<>());

                    orderMap.put(id, order);
                }
                int productId = resultSet.getInt(6);
                if (!productMap.containsKey(productId)) {
                    int quantityInOrder = resultSet.getInt(7);
                    LOGGER.info("Take productId in getAll: " + productId);
                    Product product = new Product();
                    product.setId(productId);
                    product.setQuantity(quantityInOrder);
                    productMap.put(productId, product);
                    orderMap.get(id).getProducts().add(productMap.get(productId));
                }
//                Product product = new Product();
//                product.setId(productId);
//                orderMap.get(id).getProducts().add(product);
                k++;
            }
            for (Map.Entry<Integer, Order> entry : orderMap.entrySet()) {
                orders.add(entry.getValue());
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in getOrderWithProductById: " + e);
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return orders;
    }

    public Order getById(int id) {//need to test
        Map<Integer, Order> orderMap = new HashMap<>();
        Map<Integer, Product> productMap = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_WITH_PRODUCT_ID_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            LOGGER.info("Take order with products and customer");
            int k = 0;
            while (resultSet.next()) {
                if (!orderMap.containsKey(id)) {
                    int customerId = resultSet.getInt(2);
                    LocalDate date = resultSet.getDate(3).toLocalDate();
                    OrderStatus status = OrderStatus.valueOf(resultSet.getString(4).toUpperCase());
                    double price = resultSet.getDouble(5);

                    LOGGER.trace("Cycle[" + k + "] in getOrderById, id=" + id + ", " + ", date=" + date +
                            ", status=" + status + ", price=" + price);
                    Customer customer = new Customer();
                    customer.setId(customerId);
                    Order order = new Order(id, date, status, price, customer, new ArrayList<>());

                    orderMap.put(id, order);
                }
                int productId = resultSet.getInt(6);
                if (!productMap.containsKey(productId)) {
                    int productInOrder = resultSet.getInt(7);
                    LOGGER.info("Take productId in getById: " + productId);
                    Product product = new Product();
                    product.setId(productId);
                    product.setQuantity(productInOrder);
                    productMap.put(productId, product);
                    orderMap.get(id).getProducts().add(productMap.get(productId));
                }
                Product product = new Product();
                product.setId(productId);
                orderMap.get(id).getProducts().add(product);
                k++;
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException in getOrderWithProductById: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return orderMap.get(id);
    }

//    public Order getById(Integer id) {//if flag=true, take by userId
//        Order order = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take order");
//            while (resultSet.next()) {
//                int customerId = resultSet.getInt(2);
//                LocalDate date = resultSet.getDate(3).toLocalDate();
//                OrderStatus status = OrderStatus.valueOf(resultSet.getString(4));
//                double price = resultSet.getDouble(5);
//
//                LOGGER.trace("Cycle in getById, id=" + id + ", customer_id=" + customerId + ", date=" + date + ", status=" + status +
//                        ", price=" + price);
//                Customer customer = new Customer();
//                customer.setId(customerId);
//                order = new Order(id, date, status, price, customer);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get order by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return order;
//    }

    public int insert(Order order) {//need to test
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;
        if (order.getCustomerOwner() == null || order.getProducts().isEmpty()) {
            return id;
        }
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomerOwner().getId());
            preparedStatement.setDate(2, Date.valueOf(order.getDate()));
            preparedStatement.setString(3, order.getStatus().toString());
            preparedStatement.setDouble(4, order.getPrice());

            LOGGER.info("Insert order: " + order.getDate() + " " + order.getStatus() + " " + order.getPrice());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            LOGGER.info("ID of the inserted order; " + id);
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDERPRODUCT);
            for (Product product : order.getProducts()) {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, product.getId());
                preparedStatement.setInt(3, product.getQuantity());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException in insert order: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return id;
    }

    public boolean delete(int id) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            Connection connection = ConnectionFactory.getConnection();

            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID);

            preparedStatement.setInt(1, id);
            LOGGER.info("Delete order by id=" + id);
            preparedStatement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            LOGGER.error("SQLException in delete order: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
        }
        return flag;
    }

    public boolean update(Order order) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);
            preparedStatement.setDate(1, Date.valueOf(order.getDate()));
            preparedStatement.setString(2, order.getStatus().toString());
            preparedStatement.setDouble(3, order.getPrice());
            preparedStatement.setInt(4, order.getId());
            LOGGER.info("Update order");
            preparedStatement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            LOGGER.error("SQLException in update Order: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
        }
        return flag;
    }
}
