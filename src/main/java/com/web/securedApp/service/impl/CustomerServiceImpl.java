package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.CustomerWithEntitiesDao;
import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.impl.CustomerDao;
import com.web.securedApp.model.dao.impl.CustomerWithEntitiesDaoImpl;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.service.CustomerService;
import org.apache.log4j.Logger;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
    private GenericDao<Customer> customerDao = new CustomerDao();
    private CustomerWithEntitiesDao customerWithEntitiesDao= new CustomerWithEntitiesDaoImpl();

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerDao.getAll();
        if (customers.isEmpty()) {
            LOGGER.info("List customers is null!");
            return null;
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        if (id < 0) {
            return null;
        }
        Customer customer = customerDao.getById(id);
        if (customer == null) {
            LOGGER.info("Customer is null!");
        }
        return customer;
    }

    @Override
    public int insert(Customer customer) {
        if (customer == null) {
            return -1;
        }
        int id = customerDao.insert(customer);
        if (id == -1) {
            LOGGER.error("Failed to insert customer!");
        }
        return id;
    }

    @Override
    public boolean delete(Customer customer) {
        if (customer == null) {
            return false;
        }
        boolean checkDelete = customerDao.delete(customer);
        if (!checkDelete) {
            LOGGER.info("Failed to delete customer!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = customerDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete customer by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Customer customer) {
        if (customer == null) {
            return false;
        }
        boolean checkUpdate = customerDao.update(customer);
        if (!checkUpdate) {
            LOGGER.info("Failed to update customer");
        }
        return checkUpdate;
    }

    @Override
    public Customer getCustomerWithOrderFeedbackById(int id) {
        if (id < 0) {
            return null;
        }
        Customer customer = customerWithEntitiesDao.getCustomerWithOrderFeedbackById(id);
        if (customer == null) {
            LOGGER.info("Customer with order, feedback is null!");
        }
        return customer;
    }
}
