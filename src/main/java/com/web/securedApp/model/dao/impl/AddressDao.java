package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.domain.customer.Address;
import com.web.securedApp.model.domain.customer.Customer;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao extends GenericDao<Address> {

    private static final Logger LOGGER = Logger.getLogger(AddressDao.class);

    private static final String SQL_GET_ALL_ADDRESS = "select * from address";
    private static final String SQL_GET_ADDRESS_BY_ID = "select * from address where id=?";
    private static final String SQL_INSERT_ADDRESS = "insert into address (customer_id, country, city, street, " +
            "house_flat, postcode) values (?,?,?,?,?,?)";
    private static final String SQL_DELETE_ADDRESS = "delete from address where customer_id=? and country=?" +
            "and city=? and street=? and house_flat=? and postcode=?";
    private static final String SQL_DELETE_ADDRESS_BY_ID = "delete from address where id=?";
    private static final String SQL_UPDATE_ADDRESS = "update address set country=?, city=?, street=?, house_flat=?, " +
            " postcode=? where id=?";

    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_ADDRESS;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_ADDRESS_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_ADDRESS;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_ADDRESS;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_ADDRESS_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_ADDRESS;
    }

    @Override
    protected Address parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int customerId = resultSet.getInt("customer_id");
        String country = resultSet.getString("country");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String houseFlat = resultSet.getString("house_flat");
        int postcode = resultSet.getInt("postcode");
        Customer customer = new Customer();
        customer.setId(customerId);
        LOGGER.trace("Take address, id=" + id + ", country=" + country + ", city=" + city +
                ", street=" + street + ", houseFlat=" + houseFlat +
                ", postcode=" + postcode);
        return new Address(id, country, city, street, houseFlat, postcode, customer);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, Address address) throws SQLException {
        preparedStatement.setInt(1, address.getCustomerOwner().getId());
        preparedStatement.setString(2, address.getCountry());
        preparedStatement.setString(3, address.getCity());
        preparedStatement.setString(4, address.getStreet());
        preparedStatement.setString(5, address.getHouseFlat());
        preparedStatement.setInt(6, address.getPostcode());
        LOGGER.trace("Insert or delete address: " + address.getCountry() + " " + address.getCity() + " " + address.getStreet()
                + " " + address.getHouseFlat());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, Address address) throws SQLException {
        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setString(3, address.getStreet());
        preparedStatement.setString(4, address.getHouseFlat());
        preparedStatement.setInt(5, address.getPostcode());
        preparedStatement.setInt(6, address.getId());
        LOGGER.trace("Update address");

    }


//    public List<Address> getAll() {
//        List<Address> addresses = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_ADDRESS);
//            LOGGER.info("Take addresses");
//            addresses = new ArrayList<>();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                int customerId = resultSet.getInt(2);
//                String country = resultSet.getString(3);
//                String city = resultSet.getString(4);
//                String street = resultSet.getString(5);
//                String houseFlat = resultSet.getString(6);
//                int postcode = resultSet.getInt(7);
//                LOGGER.trace("Cycle in getById, id=" + id + ", country=" + country + ", city=" + city +
//                        ", street=" + street + ", houseFlat=" + houseFlat +
//                        ", postcode=" + postcode);
//                Customer customer = new Customer();
//                customer.setId(customerId);
//                addresses.add(new Address(id, country, city, street, houseFlat, postcode, customer));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get all addresses: " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return addresses;
//    }
//
//    public Address getById(Integer id) {
//        Address address = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_ADDRESS_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take address");
//            while (resultSet.next()) {
//                int customerId = resultSet.getInt(2);
//                String country = resultSet.getString(3);
//                String city = resultSet.getString(4);
//                String street = resultSet.getString(5);
//                String houseFlat = resultSet.getString(6);
//                int postcode = resultSet.getInt(7);
//                LOGGER.trace("Cycle in getAll, id=" + id + ", customer_id=" + customerId + ", country=" + country +
//                        ", city=" + city +
//                        ", street=" + street + ", houseFlat=" + houseFlat +
//                        ", postcode=" + postcode);
//                Customer customer = new Customer();
//                customer.setId(customerId);
//                address = new Address(id, country, city, street, houseFlat, postcode, customer);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get address by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return address;
//    }
//
//    public int insert(Address address) {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int id = -1;
//        if (address.getCustomerOwner() == null) {
//            return id;
//        }
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setInt(1, address.getCustomerOwner().getId());
//            preparedStatement.setString(2, address.getCountry());
//            preparedStatement.setString(3, address.getCity());
//            preparedStatement.setString(4, address.getStreet());
//            preparedStatement.setString(5, address.getHouseFlat());
//            preparedStatement.setInt(6, address.getPostcode());
//
//            LOGGER.info("Insert address: " + address.getCountry() + " " + address.getCity() + " " + address.getStreet()
//                    + " " + address.getHouseFlat());
//            preparedStatement.executeUpdate();
//
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            LOGGER.info("ID of the inserted address; " + id);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in insert address: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return id;
//    }
//
//    public boolean delete(Address entity) {
//        return false;
//    }
//
//    public boolean delete(Integer id) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//
//            preparedStatement = connection.prepareStatement(SQL_DELETE_ADDRESS_BY_ID);
//
//            preparedStatement.setInt(1, id);
//            LOGGER.info("Delete address by id=" + id);
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete address: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean update(Address address) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_UPDATE_ADDRESS);
//
//            preparedStatement.setString(1, address.getCountry());
//            preparedStatement.setString(2, address.getCity());
//            preparedStatement.setString(3, address.getStreet());
//            preparedStatement.setString(4, address.getHouseFlat());
//            preparedStatement.setInt(5, address.getPostcode());
//            preparedStatement.setInt(6, address.getId());
//            LOGGER.info("Update address");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in update address: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }

}
