package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.Feedback;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.product.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao extends GenericDao<Feedback> {
    private static final Logger LOGGER = Logger.getLogger(FeedbackDao.class);

    private static final String SQL_GET_ALL_FEEDBACK = "select * from feedback";
    private static final String SQL_GET_FEEDBACK_BY_ID = "select * from feedback where id=?";
    private static final String SQL_INSERT_FEEDBACK = "insert into feedback (id, customer_id, product_id," +
            " review,date,time) values (?,?,?,?,?,?)";
    private static final String SQL_DELETE_FEEDBACK = "delete from feedback where customer_id=? and product_id=? " +
            "and review=? and date=? and time=?";
    private static final String SQL_DELETE_FEEDBACK_BY_ID = "delete from feedback where id=?";
    private static final String SQL_UPDATE_FEEDBACK = "update feedback set review=?, date=?, time=? where id=?";

    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_FEEDBACK;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_FEEDBACK_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_FEEDBACK;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_FEEDBACK;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_FEEDBACK_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_FEEDBACK;
    }

    @Override
    protected Feedback parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int customerId = resultSet.getInt("customer_id");
        int productId = resultSet.getInt("product_id");
        String review = resultSet.getString("review");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        LocalTime time = resultSet.getTime("time").toLocalTime();
        LOGGER.trace("Take feedback, id=" + id + ", customerId=" + customerId + ", productId=" + productId +
                ", review=" + review + ", date=" + date + ", time=" + time);
        Customer customer = new Customer();
        customer.setId(customerId);
        Product product = new Product();
        product.setId(productId);
        return new Feedback(id, review, date, time, customer, product);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, Feedback feedback) throws SQLException {
        preparedStatement.setInt(1, feedback.getId());
        preparedStatement.setInt(2, feedback.getCustomerOwner().getId());
        preparedStatement.setInt(3, feedback.getProductOwner().getId());
        preparedStatement.setString(4, feedback.getReview());
        preparedStatement.setDate(5, Date.valueOf(feedback.getDate()));
        preparedStatement.setTime(6, Time.valueOf(feedback.getTime()));
        LOGGER.trace("Insert or delete feedback: " + feedback.getReview() + " " + feedback.getDate() + " " + feedback.getDate());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, Feedback feedback) throws SQLException {
        preparedStatement.setString(1, feedback.getReview());
        preparedStatement.setDate(2, Date.valueOf(feedback.getDate()));
        preparedStatement.setTime(3, Time.valueOf(feedback.getTime()));
        preparedStatement.setInt(4, feedback.getId());
        LOGGER.trace("Update feedback");
    }

//    public List<Feedback> getAll() {
//        List<Feedback> feedbacks = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_FEEDBACK);
//            LOGGER.info("Take feedback");
//            feedbacks = new ArrayList<>();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                int customerId = resultSet.getInt(2);
//                int productId = resultSet.getInt(3);
//                String review = resultSet.getString(4);
//                LocalDate date = resultSet.getDate(5).toLocalDate();
//                LocalTime time = resultSet.getTime(6).toLocalTime();
//
//                LOGGER.trace("Cycle in getAll, id=" + id + ", customerId=" + customerId + ", productId=" + productId +
//                        ", review=" + review + ", date=" + date + ", time=" + time);
//                Customer customer = new Customer();
//                customer.setId(customerId);
//                Product product = new Product();
//                product.setId(productId);
//                feedbacks.add(new Feedback(id, review, date, time, customer, product));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get all feedbacks: " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return feedbacks;
//    }
//
//    public Feedback getById(Integer id) {
//        Feedback feedback = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_FEEDBACK_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take feedback");
//            while (resultSet.next()) {
//                int customerId = resultSet.getInt(2);
//                int productId = resultSet.getInt(3);
//                String review = resultSet.getString(4);
//                LocalDate date = resultSet.getDate(5).toLocalDate();
//                LocalTime time = resultSet.getTime(6).toLocalTime();
//
//                LOGGER.trace("Cycle in getAll, id=" + id + ", customerId=" + customerId + ", productId=" + productId +
//                        ", review=" + review + ", date=" + date + ", time=" + time);
//                Customer customer = new Customer();
//                customer.setId(customerId);
//                Product product = new Product();
//                product.setId(productId);
//                feedback = new Feedback(id, review, date, time, customer, product);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get feedback by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return feedback;
//    }
//
//    public int insert(Feedback feedback) {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int id = -1;
//        if (feedback.getProductOwner() == null || feedback.getCustomerOwner() == null) {
//            return id;
//        }
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_INSERT_FEEDBACK, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, feedback.getId());
//            preparedStatement.setInt(2, feedback.getCustomerOwner().getId());
//            preparedStatement.setInt(3, feedback.getProductOwner().getId());
//            preparedStatement.setString(4, feedback.getReview());
//            preparedStatement.setDate(5, Date.valueOf(feedback.getDate()));
//            preparedStatement.setTime(6, Time.valueOf(feedback.getTime()));
//            LOGGER.info("Insert feedback: " + feedback.getReview() + " " + feedback.getDate() + " " + feedback.getDate());
//            preparedStatement.executeUpdate();
//
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            LOGGER.info("ID of the inserted feedback; " + id);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in insert feedback: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return id;
//    }
//
//    public boolean delete(Feedback entity) {
//        return false;
//    }
//
//    public boolean delete(Integer id) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//
//            preparedStatement = connection.prepareStatement(SQL_DELETE_FEEDBACK_BY_ID);
//
//            preparedStatement.setInt(1, id);
//            LOGGER.info("Delete feedback by id=" + id);
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete feedback: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean update(Feedback feedback) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_UPDATE_FEEDBACK);
//
//            preparedStatement.setString(1, feedback.getReview());
//            preparedStatement.setDate(2, Date.valueOf(feedback.getDate()));
//            preparedStatement.setTime(3, Time.valueOf(feedback.getTime()));
//            preparedStatement.setInt(4, feedback.getId());
//
//            LOGGER.info("Update feedback");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in update feedback: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
}
