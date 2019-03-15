package com.web.securedApp.model.dao;

import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.Entity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDao<T extends Entity>{
    private final static Logger LOGGER = Logger.getLogger(GenericDao.class);

    protected abstract String getSqlGetAll();

    protected abstract String getSqlGetById();

    protected abstract String getSqlInsert();

    protected abstract String getSqlDelete();

    protected abstract String getSqlDeleteById();

    protected abstract String getSqlUpdate();


    protected abstract T parseFromSql(ResultSet resultSet) throws SQLException;

    protected abstract void fillInsertDeleteQuery(PreparedStatement preparedStatement, T entity) throws SQLException;

    protected abstract void fillUpdateQuery(PreparedStatement preparedStatement, T entity) throws SQLException;

    public List<T> getAll() {
        List<T> model = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(getSqlGetAll());
            model = new ArrayList<>();
            while (resultSet.next()) {
                model.add(parseFromSql(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in get all: " + e);
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return model;
    }

    public T getById(int id) {
        T entity = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlGetById());
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entity = parseFromSql(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in get by id: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return entity;
    }

    public int insert(T entity) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlInsert(), Statement.RETURN_GENERATED_KEYS);
            fillInsertDeleteQuery(preparedStatement, entity);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            LOGGER.info("ID of the inserted entity; " + id);
        } catch (SQLException e) {
            LOGGER.error("SQLException in insert: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
            ConnectionFactory.closeResultSet(resultSet);
        }
        return id;
    }

    public boolean delete(T entity) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlDelete());
            fillInsertDeleteQuery(preparedStatement, entity);
            preparedStatement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            LOGGER.error("SQLException in delete: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
        }
        return flag;
    }

    public boolean delete(int id) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlDeleteById());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            LOGGER.error("SQLException in delete: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
        }
        return flag;
    }

    public boolean update(T entity) {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try {
            Connection connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(getSqlUpdate());
            fillUpdateQuery(preparedStatement, entity);
            preparedStatement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            LOGGER.error("SQLException in update: " + e);
        } finally {
            ConnectionFactory.closePreparedStatement(preparedStatement);
        }
        return flag;
    }
}
