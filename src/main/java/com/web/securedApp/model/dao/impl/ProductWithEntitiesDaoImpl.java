package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.constant.OrderStatus;
import com.web.securedApp.model.dao.ProductWithEntitiesDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.Feedback;
import com.web.securedApp.model.domain.Order;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.product.Category;
import com.web.securedApp.model.domain.product.Picture;
import com.web.securedApp.model.domain.product.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductWithEntitiesDaoImpl extends ProductWithEntitiesDao {
    private static final Logger LOGGER = Logger.getLogger(ProductWithEntitiesDaoImpl.class);

    private static final String SQL_GET_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_BY_ID = "select product.*, picture.id" +
            " as picture_id, picture_path, `order`.id as order_id, `order`.customer_id as order_customer_id, " +
            "`order`.date as order_date, status, price, " +
            "feedback.id as feedback_id, feedback.customer_id as feedback_customer_id, review, feedback.date as " +
            "feedback_date, time, category.name as category_name from product left join picture on product.id=picture.product_id " +
            "left join orderproduct on product.id=orderproduct.product_id left join `order` on orderproduct.order_id" +
            " = `order`.id left join feedback on product.id = feedback.product_id left join category on " +
            "product.category_id = category.id where product.id=?";

    private static final String SQL_GET_ALL_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_FEEDBACK_BY_ID = "select product.*, picture.id " +
            "as picture_id, picture_path, `order`.id as order_id, `order`.date as order_date, status, price, " +
            "product_quantity, feedback.id as feedback_id, review, feedback.date as feedback_date, time, category.name " +
            "as category_name from product left join picture on product.id=picture.product_id " +
            "left join orderproduct on product.id=orderproduct.product_id left join `order` on orderproduct.order_id " +
            " = `order`.id left join feedback on product.id = feedback.product_id left join category on category_id=category.id";

    private static final String SQL_GET_ALL_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_FEEDBACK_BY_CATEGORY = "select product.*," +
            " picture.id as picture_id, picture_path, `order`.id as order_id, `order`.date as order_date, status, price, " +
            "product_quantity, feedback.id as feedback_id, review, feedback.date as feedback_date, time, category.name " +
            "as category_name from product left join picture on product.id=picture.product_id " +
            "left join orderproduct on product.id=orderproduct.product_id left join `order` on orderproduct.order_id " +
            " = `order`.id left join feedback on product.id = feedback.product_id left join category on " +
            "product.category_id = category.id where category_id = ?";

    @Override
    protected String getSqlGetProductWithPictureFeedbackOrderById() {
        return SQL_GET_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_BY_ID;
    }

    @Override
    protected String getSqlGetAllProductWithPictureFeedbackOrder() {
        return SQL_GET_ALL_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_FEEDBACK_BY_ID;
    }

    @Override
    protected String getSqlGetAllProductWithPictureFeedbackOrderByCategory() {
        return SQL_GET_ALL_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_FEEDBACK_BY_CATEGORY;
    }

    @Override
    protected List<Product> parseFromSqlAllProducts(ResultSet resultSet, Map<Integer, Product> productMap) throws SQLException {
        Map<Integer, Map<Integer, Picture>> productPictureMap = new HashMap<>();
        Map<Integer, Map<Integer, Order>> productOrderMap = new HashMap<>();
        Map<Integer, Map<Integer, Feedback>> productFeedbackMap = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        LOGGER.info("Take all product with order, feedback, picture");
        int k = 0;
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            if (!productMap.containsKey(id)) {
                String name = resultSet.getString("name");
                int cost = resultSet.getInt("cost");
                int quantity = resultSet.getInt("quantity");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                LOGGER.trace("Cycle[" + k + "] in getAllProductsWithPictureFeedbackOrderByCategory, name="
                        + name + ", cost=" + cost + ", quantity=" + quantity + ", description=" + description);
                Product product = new Product(id, name, cost, quantity, description, new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), category);
                productMap.put(id, product);
            }
            int pictureId = resultSet.getInt("picture_id");
            int orderId = resultSet.getInt("order_id");
            int feedbackId = resultSet.getInt("feedback_id");
            ArrayList<Product> products = new ArrayList<>();
            products.add(productMap.get(id));
            if (pictureId != 0) {
                if (!productPictureMap.containsKey(id)) {
                    productMap = getProductMapInPicture(resultSet, pictureId, productPictureMap, id, productMap);
                } else if (!productPictureMap.get(id).containsKey(pictureId)) {
                    productMap = getProductMapInPicture(resultSet, pictureId, productPictureMap, id, productMap);
                }
            }
            if (orderId != 0) {
                if (!productOrderMap.containsKey(id)) {
                    productMap = getProductMapInOrder(resultSet, orderId, productOrderMap, products, id, productMap);
                } else if (!productOrderMap.get(id).containsKey(orderId)) {
                    productMap = getProductMapInOrder(resultSet, orderId, productOrderMap, products, id, productMap);
                }
            }
            if (feedbackId != 0) {
                if (!productFeedbackMap.containsKey(id)) {
                    productMap = getProductMapInFeedback(resultSet, feedbackId, productFeedbackMap, id, productMap);
                } else if (!productFeedbackMap.get(id).containsKey(feedbackId)) {
                    productMap = getProductMapInFeedback(resultSet, feedbackId, productFeedbackMap, id, productMap);
                }
            }
            k++;
        }
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            productList.add(entry.getValue());
        }
        return productList;
    }

    public Product getProductWithPictureFeedbackOrderById(int id) {
        Map<Integer, Product> productMap = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Picture picture;
        Order order;
        Feedback feedback;
        try {
            Map<Integer, Picture> pictureMap = new HashMap<>();
            Map<Integer, Order> orderMap = new HashMap<>();
            Map<Integer, Feedback> feedbackMap = new HashMap<>();
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlGetProductWithPictureFeedbackOrderById());
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            LOGGER.info("Take product with order, feedback, picture");
            int k = 0;
            while (resultSet.next()) {
                if (!productMap.containsKey(id)) {
                    String name = resultSet.getString("name");
                    int cost = resultSet.getInt("cost");
                    int quantity = resultSet.getInt("quantity");
                    String description = resultSet.getString("description");
                    int categoryId = resultSet.getInt("category_id");
                    String categoryName = resultSet.getString("category_name");
                    Category category = new Category(categoryId, categoryName);
                    LOGGER.trace("Cycle[" + k + "] in getProductWithPictureFeedbackOrderById, name="
                            + name + ", cost=" + cost + ", quantity=" + quantity + ", description=" + description);
                    Product product = new Product(id, name, cost, quantity, description, new ArrayList<>(),
                            new ArrayList<>(), new ArrayList<>(), category);

                    productMap.put(id, product);
                }
                int pictureId = resultSet.getInt("picture_id");
                if (pictureId != 0) {
                    if (!pictureMap.containsKey(pictureId)) {
                        String picturePath = resultSet.getString("picture_path");
                        LOGGER.info("Take picture: " + pictureId + ", " + picturePath);
                        picture = new Picture(pictureId, picturePath, productMap.get(id));
                        pictureMap.put(pictureId, picture);
                        productMap.get(id).getPictures().add(pictureMap.get(pictureId));
                    }
                }
                int orderId = resultSet.getInt("order_id");
                if (orderId != 0) {
                    if (!orderMap.containsKey(orderId)) {
                        int orderCustomerId = resultSet.getInt("order_customer_id");
                        LocalDate orderDate = resultSet.getDate("order_date").toLocalDate();
                        OrderStatus status = OrderStatus.valueOf(resultSet.getString("status").toUpperCase());
                        double price = resultSet.getDouble("price");
                        ArrayList<Product> products = new ArrayList<>();
                        products.add(productMap.get(id));
                        Customer orderCustomer = new Customer();
                        orderCustomer.setId(orderCustomerId);
                        LOGGER.info("Take order: " + orderId + ", " + orderDate + ", " + status + ", " + price);
                        order = new Order(orderId, orderDate, status, price, orderCustomer, products);
                        orderMap.put(orderId, order);
                        productMap.get(id).getOrders().add(orderMap.get(orderId));
                    }
                }
                int feedbackId = resultSet.getInt("feedback_id");
                if (feedbackId != 0) {
                    if (!feedbackMap.containsKey(feedbackId)) {
                        int feedbackCustomerId = resultSet.getInt("feedback_customer_id");
                        String review = resultSet.getString("review");
                        LocalDate feedbackDate = resultSet.getDate("feedback_date").toLocalDate();
                        LocalTime time = resultSet.getTime("time").toLocalTime();
                        Customer feedbackCustomer = new Customer();
                        feedbackCustomer.setId(feedbackCustomerId);
                        LOGGER.info("Take feedback: " + feedbackId + ", " + review + ", " + feedbackDate + ", " + time);
                        feedback = new Feedback(feedbackId, review, feedbackDate, time, feedbackCustomer,
                                productMap.get(id));
                        feedbackMap.put(feedbackId, feedback);
                        productMap.get(id).getFeedbacks().add(feedbackMap.get(feedbackId));
                    }
                }
                k++;
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in getProductWithPictureFeedbackOrderById: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return productMap.get(id);
    }

//    public List<Product> getAllProductsWithPictureFeedbackOrder() {
//        List<Product> productList = null;
//        Map<Integer, Product> productMap = new HashMap<>();
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_PRODUCT_WITH_PICTURE_FEEDBACK_ORDER_FEEDBACK_BY_ID);
//            Map<Integer, Map<Integer, Picture>> productPictureMap = new HashMap<>();
//            Map<Integer, Map<Integer, Order>> productOrderMap = new HashMap<>();
//            Map<Integer, Map<Integer, Feedback>> productFeedbackMap = new HashMap<>();
//            productList = new ArrayList<>();
//            LOGGER.info("Take all product with order, feedback, picture");
//            int k = 0;
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                if (!productMap.containsKey(id)) {
//                    String name = resultSet.getString("name");
//                    int cost = resultSet.getInt("cost");
//                    int quantity = resultSet.getInt("quantity");
//                    String description = resultSet.getString("description");
//                    LOGGER.trace("Cycle[" + k + "] in getAllProductsWithPictureFeedbackOrder, name="
//                            + name + ", cost=" + cost + ", quantity=" + quantity + ", description=" + description);
//                    Product product = new Product(id, name, cost, quantity, description, new ArrayList<>(),
//                            new ArrayList<>(), new ArrayList<>());
//                    productMap.put(id, product);
//                }
//                int pictureId = resultSet.getInt("picture_id");
//                int orderId = resultSet.getInt("order_id");
//                int feedbackId = resultSet.getInt("feedback_id");
//                ArrayList<Product> products = new ArrayList<>();
//                products.add(productMap.get(id));
//                if (pictureId != 0) {
//                    if (!productPictureMap.containsKey(id)) {
//                        productMap = getProductMapInPicture(resultSet, pictureId, productPictureMap, id, productMap);
//                    } else if (!productPictureMap.get(id).containsKey(pictureId)) {
//                        productMap = getProductMapInPicture(resultSet, pictureId, productPictureMap, id, productMap);
//                    }
//                }
//                if (orderId != 0) {
//                    if (!productOrderMap.containsKey(id)) {
//                        productMap = getProductMapInOrder(resultSet, orderId, productOrderMap, products, id, productMap);
//                    } else if (!productOrderMap.get(id).containsKey(orderId)) {
//                        productMap = getProductMapInOrder(resultSet, orderId, productOrderMap, products, id, productMap);
//                    }
//                }
//                if (feedbackId != 0) {
//                    if (!productFeedbackMap.containsKey(id)) {
//                        productMap = getProductMapInFeedback(resultSet, feedbackId, productFeedbackMap, id, productMap);
//                    } else if (!productFeedbackMap.get(id).containsKey(feedbackId)) {
//                        productMap = getProductMapInFeedback(resultSet, feedbackId, productFeedbackMap, id, productMap);
//                    }
//                }
//                k++;
//            }
//            for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
//                productList.add(entry.getValue());
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in getAllProductsWithPictureFeedbackOrder: " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return productList;
//    }

    private Map<Integer, Product> getProductMapInPicture(ResultSet resultSet, int pictureId, Map<Integer, Map<Integer,
            Picture>> productPictureMap, int id, Map<Integer, Product> productMap) throws SQLException {
        Map<Integer, Picture> pictureMap = new HashMap<>();
        String picturePath = resultSet.getString("picture_path");
        LOGGER.info("Take picture: " + pictureId + ", " + picturePath);
        Picture picture = new Picture(pictureId, picturePath, productMap.get(id));
        pictureMap.clear();
        pictureMap.put(pictureId, picture);
        productPictureMap.put(id, pictureMap);
        productMap.get(id).getPictures().add(pictureMap.get(pictureId));
        return productMap;
    }

    private Map<Integer, Product> getProductMapInOrder(ResultSet resultSet, int orderId, Map<Integer, Map<Integer,
            Order>> productOrderMap, ArrayList<Product> products, int id, Map<Integer, Product> productMap)
            throws SQLException {
        Map<Integer, Order> orderMap = new HashMap<>();
        LocalDate orderDate = resultSet.getDate("order_date").toLocalDate();
        OrderStatus status = OrderStatus.valueOf(resultSet.getString("status").toUpperCase());
        double price = resultSet.getDouble("price");
        LOGGER.info("Take order: " + orderId + ", " + orderDate + ", " + status + ", " + price);
        Order order = new Order(orderId, orderDate, status, price, products);
        orderMap.clear();
        orderMap.put(orderId, order);
        productOrderMap.put(id, orderMap);
        productMap.get(id).getOrders().add(orderMap.get(orderId));
        return productMap;
    }

    private Map<Integer, Product> getProductMapInFeedback(ResultSet resultSet, int feedbackId, Map<Integer, Map<Integer,
            Feedback>> productFeedbackMap, int id, Map<Integer, Product> productMap) throws SQLException {
        Map<Integer, Feedback> feedbackMap = new HashMap<>();
        String review = resultSet.getString("review");
        LocalDate feedbackDate = null;
        if (resultSet.getDate("feedback_date") != null) {
            feedbackDate = resultSet.getDate("feedback_date").toLocalDate();
        }
        LocalTime time = null;
        if (resultSet.getTime("time") != null) {
            time = resultSet.getTime("time").toLocalTime();
        }
        LOGGER.info("Take feedback: " + feedbackId + ", " + review + ", " + feedbackDate + ", " + time);
        Feedback feedback = new Feedback(feedbackId, review, feedbackDate, time, productMap.get(id));
        feedbackMap.clear();
        feedbackMap.put(feedbackId, feedback);
        productFeedbackMap.put(id, feedbackMap);
        productMap.get(id).getFeedbacks().add(feedbackMap.get(feedbackId));
        return productMap;
    }

}
