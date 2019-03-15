package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.UserLogPassDao;
import com.web.securedApp.model.dao.impl.CustomerDao;
import com.web.securedApp.model.dao.impl.UserDao;
import com.web.securedApp.model.dao.impl.UserLogPassDaoImpl;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.customer.User;
import com.web.securedApp.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private GenericDao<User> userDao = new UserDao();
    private GenericDao<Customer> customerDao = new CustomerDao();
    private UserLogPassDao userLogPassDao = new UserLogPassDaoImpl();

    @Override
    public List<User> getAll() {
        List<User> users = userDao.getAll();
        if (users.isEmpty()) {
            LOGGER.info("List user is null!");
            return null;
        }
        return users;
    }

    @Override
    public User getById(int id) {
        if (id < 0) {
            return null;
        }
        User user = userDao.getById(id);
        if (user == null) {
            LOGGER.info("User is null!");
        }
        return user;
    }

    @Override
    public int insert(User user) {
        if (user == null) {
            return -1;
        }
        int id = userDao.insert(user);
        if (id == -1) {
            LOGGER.error("Failed to insert user!");
        }
        return id;
    }

    @Override
    public boolean delete(User user) {
        if (user == null) {
            return false;
        }
        boolean checkDelete = userDao.delete(user);
        if (!checkDelete) {
            LOGGER.info("Failed to delete user!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = userDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete user by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(User user) {
        if (user == null) {
            return false;
        }
        boolean checkUpdate = userDao.update(user);
        if (!checkUpdate) {
            LOGGER.info("Failed to update user");
        }
        return checkUpdate;
    }

    @Override
    public int checkLogPass(String login, String password) {
        User user = userLogPassDao.getByLogPass(login, password);
        if (user == null) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public int registration(Customer customer, String password) {
        int id = -1;
        id = customerDao.insert(customer);
        if (id != -1) {
            userDao.insert(new User(id, customer.getEmail(), password));
        }else{
            LOGGER.info("Failed to register user");
        }
        return id;
    }
}
