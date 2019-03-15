package com.web.securedApp.model.dao;

import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.product.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ProductWithEntitiesDao {
    private final static Logger LOGGER = Logger.getLogger(ProductWithEntitiesDao.class);

    protected abstract String getSqlGetProductWithPictureFeedbackOrderById();

    protected abstract String getSqlGetAllProductWithPictureFeedbackOrder();

    protected abstract String getSqlGetAllProductWithPictureFeedbackOrderByCategory();

    protected abstract List<Product> parseFromSqlAllProducts(ResultSet resultSet, Map<Integer, Product> productMap) throws SQLException;



    public abstract Product getProductWithPictureFeedbackOrderById(int id);

    public List<Product> getAllProductsWithPictureFeedbackOrder() {
        List<Product> productList = null;
        Map<Integer, Product> productMap = new HashMap<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getSqlGetAllProductWithPictureFeedbackOrder());
            productList = parseFromSqlAllProducts(resultSet, productMap);

        } catch (SQLException e) {
            LOGGER.error("SQLException in getAllProductsWithPictureFeedbackOrder: " + e);
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return productList;
    }

    public List<Product> getAllProductWithPictureFeedbackOrderByCategory(int id) {
        List<Product> productList = null;
        Map<Integer, Product> productMap = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlGetAllProductWithPictureFeedbackOrderByCategory());
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            productList = parseFromSqlAllProducts(resultSet, productMap);

        } catch (SQLException e) {
            LOGGER.error("SQLException in getAllProductWithPictureFeedbackOrderByCategory: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return productList;
    }


}
