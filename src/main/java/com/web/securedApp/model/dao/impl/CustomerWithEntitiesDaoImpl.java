package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.constant.OrderStatus;
import com.web.securedApp.model.dao.CustomerWithEntitiesDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.Feedback;
import com.web.securedApp.model.domain.Order;
import com.web.securedApp.model.domain.customer.Address;
import com.web.securedApp.model.domain.customer.Customer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerWithEntitiesDaoImpl implements CustomerWithEntitiesDao {
    private static final Logger LOGGER = Logger.getLogger(CustomerWithEntitiesDaoImpl.class);

    private static final String SQL_GET_CUSTOMER_WITH_ADDRESS_ORDER_FEEDBACK_BY_ID = "select customer.*, address.id" +
            " as address_id, country, city, street, house_flat, postcode, `order`.id as order_id, `order`.date as " +
            "order_date, status, price, feedback.id as feedback_id, review, feedback.date as feedback_date, time " +
            "from customer left join address on customer.id = address.customer_id left join `order` on customer.id = " +
            "`order`.customer_id left join feedback on customer.id = feedback.customer_id where customer.id=?";


    public Customer getCustomerWithOrderFeedbackById(int id) {//deal with orders
        Map<Integer, Customer> customerMap = new HashMap<>();
        Map<Integer, Address> addressMap = new HashMap<>();
        Map<Integer, Order> orderMap = new HashMap<>();
        Map<Integer, Feedback> feedbackMap = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Address address;
        Order order;
        Feedback feedback;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_CUSTOMER_WITH_ADDRESS_ORDER_FEEDBACK_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            LOGGER.info("Take customer with order, feedback, address");
            int k = 0;
            while (resultSet.next()) {
                if (!customerMap.containsKey(id)) {
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    String gender = resultSet.getString(4);
                    LocalDate dateOfBirthday = resultSet.getDate(5).toLocalDate();
                    String email = resultSet.getString(6);
                    String phoneNumber = resultSet.getString(7);
                    Customer customer = new Customer(id, name, surname, gender, dateOfBirthday, email,
                            phoneNumber, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    LOGGER.trace("Cycle[" + k + "] in getCustomerWithOrderFeedbackById, name=" + name
                            + ", surname=" + surname + ", gender=" + gender + ", dateOfBirthday=" + dateOfBirthday +
                            ", email=" + email + ", phoneNumber=" + phoneNumber);
                    customerMap.put(id, customer);
                }
                int addressId = resultSet.getInt(8);
                if (addressId != 0) {
                    if (!addressMap.containsKey(addressId)) {
                        String country = resultSet.getString(9);
                        String city = resultSet.getString(10);
                        String street = resultSet.getString(11);
                        String houseFlat = resultSet.getString(12);
                        int postcode = resultSet.getInt(13);
                        LOGGER.info("Take address: " + addressId + ", " + country + ", " + city + ", " + street + ", "
                                + houseFlat + ", " + postcode);
                        address = new Address(addressId, country, city, street, houseFlat, postcode, customerMap.get(id));
                        addressMap.put(addressId, address);
                        customerMap.get(id).getAddresses().add(addressMap.get(addressId));
                    }
                }

                int orderId = resultSet.getInt(14);
                if (orderId != 0) {
                    if (!orderMap.containsKey(orderId)) {
                        LocalDate orderDate = resultSet.getDate(15).toLocalDate();
                        OrderStatus status = OrderStatus.valueOf(resultSet.getString(16).toUpperCase());
                        double price = resultSet.getDouble(17);
                        LOGGER.info("Take order: " + orderId + ", " + orderDate + ", " + status + ", " + price);
                        order = new Order(orderId, orderDate, status, price, customerMap.get(id));
                        orderMap.put(orderId, order);
                        customerMap.get(id).getOrders().add(orderMap.get(orderId));
                    }
                }
                int feedbackId = resultSet.getInt(18);
                if (feedbackId != 0) {
                    if (!feedbackMap.containsKey(feedbackId)) {
                        String review = resultSet.getString(19);
                        LocalDate feedbackDate = resultSet.getDate(20).toLocalDate();
                        LocalTime time = resultSet.getTime(21).toLocalTime();
                        LOGGER.info("Take feedback: " + feedbackId + ", " + review + ", " + feedbackDate + ", " + time);
                        feedback = new Feedback(feedbackId, review, feedbackDate, time, customerMap.get(id));
                        feedbackMap.put(feedbackId, feedback);
                        customerMap.get(id).getFeedbacks().add(feedbackMap.get(feedbackId));
                    }
                }
                k++;
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException in getCustomerWithOrderFeedbackById: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return customerMap.get(id);
    }


//    public Order getOrderWithProductById(Integer id) {
//        Map<Integer, Order> orderMap = new HashMap<>();
//        Map<Integer, Product> productMap = new HashMap<>();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Product product;
//        Customer customer;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_WITH_PRODUCT_CUSTOMER_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take order with products and customer");
//            int k = 0;
//            while (resultSet.next()) {
//                if (!orderMap.containsKey(id)) {
//                    int customerId = resultSet.getInt(2);
//                    LocalDate date = resultSet.getDate(3).toLocalDate();
//                    OrderStatus status = OrderStatus.valueOf(resultSet.getString(4));
//                    double price = resultSet.getDouble(5);
//
//                    LOGGER.trace("Cycle[" + k + "] in getOrderWithProductById, id=" + id + ", " + ", date=" + date +
//                            ", status=" + status + ", price=" + price);
//                    customer = new Customer();
//                    customer.setId(customerId);
//                    Order order = new Order(id, date, status, price, customer, new ArrayList<>());
//
//                    orderMap.put(id, order);
//                }
//                int productId = resultSet.getInt(6);
//                if (!productMap.containsKey(productId)) {
//                    LOGGER.info("Take productId: " + productId);
//                    product = new Product();
//                    product.setId(productId);
//                    productMap.put(productId, product);
//                    orderMap.get(id).getProducts().add(productMap.get(productId));
//                }
//                k++;
//            }
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in getOrderWithProductById: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return orderMap.get(id);
//    }


//написать метод для вставки айдишников в orderproduct при заказе пользователя
//написать метод для забирания всех продуктов с их картинками и тд(просто брать запрос без айди!!)
//но брать буду не полностью а только с картинками и количеством отзывов и заказов


//    public Feedback getFeedbackWithCustomerProductById(Integer id){
//        Map<Integer, Feedback> feedbackMap = new HashMap<>();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        Product product;
//        Customer customer;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_FEEDBACK_WITH_PRODUCT_CUSTOMER_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take feedback with product");
//            int k = 0;
//            while (resultSet.next()) {
//                if (!feedbackMap.containsKey(id)) {
//                    int customerId = resultSet.getInt(2);
//                    int productId = resultSet.getInt(3);
//                    String review = resultSet.getString(4);
//                    LocalDate date = resultSet.getDate(5).toLocalDate();
//                    LocalTime time = resultSet.getTime(6).toLocalTime();
//
//                    LOGGER.trace("Cycle in getAll, id=" + id + ", customerId=" + customerId + ", productId=" + productId +
//                            ", review=" + review + ", date=" + date + ", time=" + time);
//                    customer = new Customer();
//                    customer.setId(customerId);
//                    product = new Product();
//                    product.setId(productId);
//                    Feedback feedback = new Feedback(id, review, date, time, customer, product);
//
//
//                    feedbackMap.put(id, order);
//                }
//                int productId = resultSet.getInt(6);
//                LOGGER.info("Take productId: " + productId);
//                product = new Product();
//                product.setId(productId);
//
//                feedbackMap.get(id).getProducts().add(product);
//                k++;
//            }
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in getOrderWithProductById: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return feedbackMap.get(id);
//    }
}
