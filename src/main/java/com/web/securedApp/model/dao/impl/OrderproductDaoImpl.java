package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.OrderproductDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.Order;
import org.apache.log4j.Logger;

import java.sql.*;

public class OrderproductDaoImpl implements OrderproductDao {

    private static final Logger LOGGER = Logger.getLogger(OrderproductDaoImpl.class);

    private static final String SQL_DELETE_ORDERPRODUCT_BY_ID = "delete from `orderproduct` where order_id=? and " +
            "product_id=?";
    private static final String SQL_INSERT_ORDERPRODUCT = "insert into orderproduct (order_id, product_id, " +
            "product_quantity) values (?,?,?)";

    public boolean deleteById(int orderId, int productId) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDERPRODUCT_BY_ID);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            LOGGER.info("Delete order by id=" + orderId + ", " + productId);
            preparedStatement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            LOGGER.error("SQLException in delete order: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
        }
        return flag;
    }

    public int insert(Order order) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;
        if (order.getProducts().isEmpty()) {
            return id;
        }
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDERPRODUCT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getProducts().get(0).getId());
            preparedStatement.setInt(3, order.getProducts().get(0).getQuantity());
            LOGGER.info("Insert order: " + order.getDate() + " " + order.getStatus() + " " + order.getPrice());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException in insert order: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return id;
    }
}
