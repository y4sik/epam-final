package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.connector.ConnectionFactory;
import com.web.securedApp.model.domain.product.Picture;
import com.web.securedApp.model.domain.product.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PictureDao extends GenericDao<Picture> {
    private static final Logger LOGGER = Logger.getLogger(PictureDao.class);

    private static final String SQL_GET_ALL_PICTURE = "select * from picture";
    //private static final String SQL_GET_PICTURE_BY_PRODUCT_ID = "select * from picture where product_id=?";
    private static final String SQL_GET_PICTURE_BY_ID = "select * from picture where id=?";

    private static final String SQL_INSERT_PICTURE = "insert into picture (product_id, picture_path) " +
            "values (?,?)";
    private static final String SQL_DELETE_PICTURE = "delete from picture where product_id=? and picture_path=?";
    private static final String SQL_DELETE_PICTURE_BY_ID = "delete from picture where id=?";
    private static final String SQL_UPDATE_PICTURE = "update picture set picture_path=? where id=?";

    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_PICTURE;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_PICTURE_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_PICTURE;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_PICTURE;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_PICTURE_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_PICTURE;
    }

    @Override
    protected Picture parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int productId = resultSet.getInt("product_id");
        String picturePath = resultSet.getString("picture_path");
        LOGGER.trace("Take picture, id=" + id + ", user_id=" + productId + ", picturePath=" + picturePath);
        Product product = new Product();
        product.setId(productId);
        return new Picture(id, picturePath, product);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, Picture picture) throws SQLException {
        preparedStatement.setInt(1, picture.getProductOwner().getId());
        preparedStatement.setString(2, picture.getPicturePath());
        LOGGER.trace("Insert or delete picture: " + picture.getPicturePath());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, Picture picture) throws SQLException {
        preparedStatement.setString(1, picture.getPicturePath());
        preparedStatement.setInt(2, picture.getId());
        LOGGER.trace("Update picture");
    }

//    public List<Picture> getAll() {
//        List<Picture> pictures = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_PICTURE);
//            LOGGER.info("Take picture");
//            pictures = new ArrayList<>();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                int productId = resultSet.getInt(2);
//                String picturePath = resultSet.getString(3);
//
//                LOGGER.trace("Cycle in getAll, id=" + id + ", picturePath=" + picturePath);
//                Product product = new Product();
//                product.setId(productId);
//                pictures.add(new Picture(id, picturePath, product));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get all pictures: " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return pictures;
//    }
//
//    public Picture getById(Integer id) {
//        Picture picture = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_PICTURE_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take picture");
//            while (resultSet.next()) {
//                int productId = resultSet.getInt(2);
//                String picturePath = resultSet.getString(3);
//
//                LOGGER.trace("Cycle in getById, id=" + id + ", user_id=" + productId + ", picturePath=" + picturePath);
//                Product product = new Product();
//                product.setId(productId);
//                picture = new Picture(id, picturePath, product);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get picture by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return picture;
//    }
//
//    public int insert(Picture picture) {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int id = -1;
//        if (picture.getProductOwner() == null) {
//            return id;
//        }
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_INSERT_PICTURE, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, picture.getProductOwner().getId());
//            preparedStatement.setString(2, picture.getPicturePath());
//            LOGGER.info("Insert picture: " + picture.getPicturePath());
//            preparedStatement.executeUpdate();
//
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            LOGGER.info("ID of the inserted picture; " + id);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in insert picture: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return id;
//    }
//
//    public boolean delete(Picture entity) {
//        return false;
//    }
//
//    public boolean delete(Integer id) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//
//            preparedStatement = connection.prepareStatement(SQL_DELETE_PICTURE_BY_ID);
//
//            preparedStatement.setInt(1, id);
//            LOGGER.info("Delete picture by id=" + id);
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete picture: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean update(Picture picture) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_UPDATE_PICTURE);
//
//            preparedStatement.setString(1, picture.getPicturePath());
//            preparedStatement.setInt(2, picture.getId());
//
//            LOGGER.info("Update picture");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in update picture: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
}
