package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.UserLogPassDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.customer.User;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserLogPassDaoImpl implements UserLogPassDao {
    private static final Logger LOGGER = Logger.getLogger(UserLogPassDao.class);
    private static final String SQL_CHECK_USER_LOGIN_PASSWORD = "select * from user where username=? and password=?";
    private static final String SQL_INSERT_CUSTOMER="insert ignore into customer (name, surname, gender," +
            " date_of_birthday, email, phone_number) values (?,?,?,?,?,?)";
    private static final String SQL_INSERT_USER = "insert into user (customer_id ,username, password) values (?, ?, ?)";
    private static final String SQL_C = "select * from user where username=? and password=?";

    @Override
    public User getByLogPass(String login, String password) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_USER_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                user = new User(id, login, password);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in get user by logPass: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return user;
    }


}
