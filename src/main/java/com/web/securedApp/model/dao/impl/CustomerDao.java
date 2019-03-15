package com.web.securedApp.model.dao.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.domain.customer.Customer;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerDao extends GenericDao<Customer> {
    private static final Logger LOGGER = Logger.getLogger(CustomerDao.class);

    private static final String SQL_GET_ALL_CUSTOMERS = "select * from customer";
    private static final String SQL_GET_CUSTOMER_BY_ID = "select * from customer where id=?";
    private static final String SQL_INSERT_CUSTOMER = "insert ignore into customer (name, surname, gender," +
            "date_of_birthday, email, phone_number) values (?,?,?,?,?,?)";
    private static final String SQL_DELETE_CUSTOMER = "delete from customer where name=? and surname=? " +
            "and gender=? and date_of_birthday=? and email=? and phone_number=?";
    private static final String SQL_DELETE_CUSTOMER_BY_ID = "delete from customer where id=?";
    private static final String SQL_UPDATE_CUSTOMER = "update customer set name=?, surname=?," +
            " gender=?, date_of_birthday=?, email=?, phone_number=? where id=?";

    //need to test
    @Override
    protected String getSqlGetAll() {
        return SQL_GET_ALL_CUSTOMERS;
    }

    @Override
    protected String getSqlGetById() {
        return SQL_GET_CUSTOMER_BY_ID;
    }

    @Override
    protected String getSqlInsert() {
        return SQL_INSERT_CUSTOMER;
    }

    @Override
    protected String getSqlDelete() {
        return SQL_DELETE_CUSTOMER;
    }

    @Override
    protected String getSqlDeleteById() {
        return SQL_DELETE_CUSTOMER_BY_ID;
    }

    @Override
    protected String getSqlUpdate() {
        return SQL_UPDATE_CUSTOMER;
    }

    @Override
    protected Customer parseFromSql(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String gender = resultSet.getString("gender");
        LocalDate dateOfBirthday = resultSet.getDate("date_of_birthday").toLocalDate();
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phone_number");
        LOGGER.trace("Take customer, id=" + id + "name=" + name + ", surname=" + surname +
                        ", gender=" + gender + ", dateOfBirthday=" + dateOfBirthday + ", email=" + email +
                        ", phoneNumber=" + phoneNumber);
        return new Customer(id, name, surname, gender, dateOfBirthday, email, phoneNumber);
    }

    @Override
    protected void fillInsertDeleteQuery(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getSurname());
        preparedStatement.setString(3, customer.getGender());
        preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirthday()));
        preparedStatement.setString(5, customer.getEmail());
        preparedStatement.setString(6, customer.getPhoneNumber());
        LOGGER.trace("Insert or delete customer: " + customer.getName() + " " + customer.getSurname());
    }

    @Override
    protected void fillUpdateQuery(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getSurname());
        preparedStatement.setString(3, customer.getGender());
        preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirthday()));
        preparedStatement.setString(5, customer.getEmail());
        preparedStatement.setString(6, customer.getPhoneNumber());
        preparedStatement.setInt(7, customer.getId());
        LOGGER.trace("Update customer");
    }


//    public List<Customer> getAll() {
//        List<Customer> customers = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(SQL_GET_ALL_CUSTOMERS);
//            LOGGER.info("Take customer");
//            customers = new ArrayList<>();
//            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
//                String name = resultSet.getString(2);
//                String surname = resultSet.getString(3);
//                String gender = resultSet.getString(4);
//                LocalDate dateOfBirthday = resultSet.getDate(5).toLocalDate();
//                String email = resultSet.getString(6);
//                String phoneNumber = resultSet.getString(7);
//                LOGGER.trace("Cycle in getAll, id=" + id + "name=" + name + ", surname=" + surname +
//                        ", gender=" + gender + ", dateOfBirthday=" + dateOfBirthday + ", email=" + email +
//                        ", phoneNumber=" + phoneNumber);
//                customers.add(new Customer(id, name, surname, gender, dateOfBirthday, email, phoneNumber));
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get all customers: " + e);
//        } finally {
//            ConnectionFactory.closeStatement(statement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return customers;
//    }
//
//    public Customer getById(Integer id) {
//        Customer customer = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_GET_CUSTOMER_BY_ID);
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//            LOGGER.info("Take customer");
//            while (resultSet.next()) {
//                String name = resultSet.getString(2);
//                String surname = resultSet.getString(3);
//                String gender = resultSet.getString(4);
//                LocalDate dateOfBirthday = resultSet.getDate(5).toLocalDate();
//                String email = resultSet.getString(6);
//                String phoneNumber = resultSet.getString(7);
//                LOGGER.trace("Cycle in getById, id=" + id + "name=" + name + ", surname=" + surname +
//                        ", gender=" + gender + ", dateOfBirthday=" + dateOfBirthday + ", email=" + email +
//                        ", phoneNumber=" + phoneNumber);
//                customer = new Customer(id, name, surname, gender, dateOfBirthday, email, phoneNumber);
//            }
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in get customer by id: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return customer;
//    }
//
//    public int insert(Customer customer) {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int id = -1;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            preparedStatement = connection.prepareStatement(SQL_INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setString(1, customer.getName());
//            preparedStatement.setString(2, customer.getSurname());
//            preparedStatement.setString(3, customer.getGender());
//            preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirthday()));
//            preparedStatement.setString(5, customer.getEmail());
//            preparedStatement.setString(6, customer.getPhoneNumber());
//            LOGGER.info("Insert customer: " + customer.getName() + " " + customer.getSurname());
//            preparedStatement.executeUpdate();
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getInt(1);
//            }
//            LOGGER.info("ID of the inserted customer; " + id);
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in insert customer: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//            ConnectionFactory.closeResultSet(resultSet);
//        }
//        return id;
//    }
//
//    public boolean delete(Customer customer) {
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//
//            preparedStatement = connection.prepareStatement(SQL_DELETE_CUSTOMER);
//            preparedStatement.setString(1, customer.getName());
//            preparedStatement.setString(2, customer.getSurname());
//            preparedStatement.setString(3, customer.getGender());
//            preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirthday()));
//            preparedStatement.setString(5, customer.getEmail());
//            preparedStatement.setString(6, customer.getPhoneNumber());
//            LOGGER.info("Delete customer: " + customer.getName() + " " + customer.getSurname());
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete customer: " + e);
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
//            preparedStatement = connection.prepareStatement(SQL_DELETE_CUSTOMER_BY_ID);
//            preparedStatement.setInt(1, id);
//            LOGGER.info("Delete customer by id=" + id);
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in delete customer: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }
//
//    public boolean update(Customer customer) {
//
//        PreparedStatement preparedStatement = null;
//        boolean flag = false;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//
//            preparedStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER);
//
//            preparedStatement.setString(1, customer.getName());
//            preparedStatement.setString(2, customer.getSurname());
//            preparedStatement.setString(3, customer.getGender());
//            preparedStatement.setDate(4, Date.valueOf(customer.getDateOfBirthday()));
//            preparedStatement.setString(5, customer.getEmail());
//            preparedStatement.setString(6, customer.getPhoneNumber());
//            preparedStatement.setInt(7, customer.getId());
//
//            LOGGER.info("Update customer");
//            preparedStatement.executeUpdate();
//            flag = true;
//
//        } catch (SQLException e) {
//            LOGGER.error("SQLException in update customer: " + e);
//        } finally {
//            ConnectionFactory.closePreparedStatement(preparedStatement);
//        }
//        return flag;
//    }

}
