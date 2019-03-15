package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.customer.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends GenericDao<User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    private static final String SQL_GET_ALL_USERS = "select * from user";
    private static final String SQL_GET_USER_BY_ID = "select * from user where customer_id=?";
    private static final String SQL_INSERT_USER = "insert into user (customer_id ,username, password) values (?, ?, ?)";
    private static final String SQL_DELETE_USER = "delete from user where username=? and password=?";
    private static final String SQL_DELETE_USER_BY_ID = "delete from user where customer_id=?";
    private static final String SQL_UPDATE_USER = "update user set username=?, password=? where customer_id=?";

    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_USERS;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_USER_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_USER;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_USER;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_USER_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_USER;
    }

    @Override
    protected User parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("customer_id");
        String name = resultSet.getString("username");
        String password = resultSet.getString("password");
        LOGGER.trace("Take user, name=" + name + ", password=" + password);
        return  new User(id, name, password);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        LOGGER.trace("Insert or delete user");
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getId());
        LOGGER.info("Update user");
    }

    //all ok
//    public List<User> getAll() {
//        List<User> users = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_USERS);
//            LOGGER.info("take user");
//            users = new ArrayList<User>();
//            while (resultSet.next()) {
//                LOGGER.trace("cycle in getAll");
//                int id = resultSet.getInt(1);
//                String name = resultSet.getString(2);
//                String password = resultSet.getString(3);
//                users.add(new User(id, name, password));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQL exception (request or table failed): " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return users;
//    }
//
//    public User getById(Integer id) {
//        User user = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take user");
//            while (resultSet.next()) {
//                String name = resultSet.getString(2);
//                String password = resultSet.getString(3);
//                LOGGER.trace("Cycle in getById, name=" + name + ", password=" + password);
//                user = new User(id, name, password);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get all users: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return user;
//    }
//
//    public int insert(User user) {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int id = -1;
//        LOGGER.info(user.getUserName() + " " + user.getPassword());
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
//
//            preparedStatement.setInt(1, user.getId());
//            preparedStatement.setString(2, user.getUserName());
//            preparedStatement.setString(3, user.getPassword());
//            LOGGER.info("Insert user");
//            preparedStatement.executeUpdate();
//
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            LOGGER.info("ID of the inserted user; " + id);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in insert user: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return id;
//    }
//
//    public boolean delete(User user) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        LOGGER.info(user.getUserName() + " " + user.getPassword());
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
//
//            preparedStatement.setString(1, user.getUserName());
//            preparedStatement.setString(2, user.getPassword());
//
//            preparedStatement.executeUpdate();
//            LOGGER.info("delete user");
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete user: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean delete(Integer id) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
//
//            preparedStatement.setInt(1, id);
//            LOGGER.info("Delete user by id");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete user by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean update(User user) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
//
//            preparedStatement.setString(1, user.getUserName());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setInt(3, user.getId());
//            LOGGER.info("Update user");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in update user: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }

}
