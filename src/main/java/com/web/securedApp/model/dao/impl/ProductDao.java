package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.domain.product.Category;
import com.web.securedApp.model.domain.product.Product;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends GenericDao<Product> {
    private static final Logger LOGGER = Logger.getLogger(ProductDao.class);

    private static final String SQL_GET_ALL_PRODUCTS = "select * from product";
    private static final String SQL_GET_PRODUCT_BY_ID = "select * from product where id=?";
    private static final String SQL_INSERT_PRODUCT = "insert into product (name, cost, quantity, description) values (?,?,?,?)";
    private static final String SQL_DELETE_PRODUCT = "delete from product where name=? and cost=? and quantity=? and description=?";
    private static final String SQL_DELETE_PRODUCT_BY_ID = "delete from product where id=?";
    private static final String SQL_UPDATE_PRODUCT = "update product set name=?, cost=?, quantity=?, description=? where id=?";


    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_PRODUCTS;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_PRODUCT_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_PRODUCT;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_PRODUCT;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_PRODUCT_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_PRODUCT;
    }

    @Override
    protected Product parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int cost = resultSet.getInt("cost");
        int quantity = resultSet.getInt("quantity");
        String description = resultSet.getString("description");
        int categoryId = resultSet.getInt("category_id");
        Category category = new Category();
        category.setId(categoryId);
        LOGGER.trace("Take product, name=" + name + ", cost=" + cost +
                ", quantity=" + quantity + ", description=" + description);
        return new Product(id, name, cost, quantity, description, category);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getCost());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getDescription());
        LOGGER.trace("Insert or delete product: " + product.getName());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getCost());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getDescription());
        preparedStatement.setInt(5, product.getId());
        LOGGER.info("Update product");
    }

//    public List<Product> getAll() {
//        List<Product> products = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_PRODUCTS);
//            LOGGER.info("Take product");
//            products = new ArrayList<Product>();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                String name = resultSet.getString(2);
//                int cost = resultSet.getInt(3);
//                int quantity = resultSet.getInt(4);
//                String description = resultSet.getString(5);
//                LOGGER.trace("Cycle in getAll, id=" + id + "name=" + name + ", cost=" + cost +
//                        ", quantity=" + quantity + ", description=" + description);
//                products.add(new Product(id, name, cost, quantity, description));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get all products: " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return products;
//    }
//
//    public Product getById(Integer id) {
//        Product product = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_PRODUCT_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take product");
//            while (resultSet.next()) {
//                String name = resultSet.getString(2);
//                int cost = resultSet.getInt(3);
//                int quantity = resultSet.getInt(4);
//                String description = resultSet.getString(5);
//                LOGGER.trace("Cycle in getById, name=" + name + ", cost=" + cost +
//                        ", quantity=" + quantity + ", description=" + description);
//                product = new Product(id, name, cost, quantity, description);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get product by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return product;
//    }
//
//    public int insert(Product product) {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        int id = -1;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
//
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setInt(2, product.getCost());
//            preparedStatement.setInt(3, product.getQuantity());
//            preparedStatement.setString(4, product.getDescription());
//            LOGGER.info("Insert product: " + product.getName());
//            preparedStatement.executeUpdate();
//
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            LOGGER.info("ID of the inserted product; " + id);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in insert product: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return id;
//    }
//
//    public boolean delete(Product product) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
//
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setInt(2, product.getCost());
//            preparedStatement.setInt(3, product.getQuantity());
//            preparedStatement.setString(4, product.getDescription());
//            LOGGER.info("Delete product: " + product.getName());
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete product: " + e);
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
//            preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_BY_ID);
//
//            preparedStatement.setInt(1, id);
//            LOGGER.info("Delete product by id");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete product by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean update(Product product) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
//
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setInt(2, product.getCost());
//            preparedStatement.setInt(3, product.getQuantity());
//            preparedStatement.setString(4, product.getDescription());
//            preparedStatement.setInt(5, product.getId());
//            LOGGER.info("Update product");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in update product: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
}
