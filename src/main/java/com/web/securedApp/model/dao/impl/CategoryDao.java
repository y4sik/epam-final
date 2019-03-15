package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.domain.product.Category;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao extends GenericDao<Category> {

    private static final Logger LOGGER = Logger.getLogger(CategoryDao.class);

    private static final String SQL_GET_ALL_CATEGORY = "select * from category";
    private static final String SQL_GET_CATEGORY_BY_ID = "select * from category where id=?";
    private static final String SQL_INSERT_CATEGORY = "insert into category name=?";
    private static final String SQL_DELETE_CATEGORY = "delete from category where name=?";
    private static final String SQL_DELETE_CATEGORY_BY_ID = "delete from category where id=?";
    private static final String SQL_UPDATE_CATEGORY = "update category set name=?";

    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_CATEGORY;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_CATEGORY_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_CATEGORY;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_CATEGORY;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_CATEGORY_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_CATEGORY;
    }

    @Override
    protected Category parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        LOGGER.trace("Take category, id=" + id + ", name=" + name);
        return new Category(id, name);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, Category category) throws SQLException {
        preparedStatement.setInt(1, category.getId());
        preparedStatement.setString(2, category.getName());
        LOGGER.trace("Insert or delete picture: " + category.getName());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, Category category) throws SQLException {
        preparedStatement.setString(1, category.getName());
        LOGGER.trace("Update category");
    }
}
