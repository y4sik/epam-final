package com.web.securedApp.service;

import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.customer.User;

import java.util.List;

public interface UserService extends PersistentService<User>{

    int checkLogPass(String login, String password);
    int registration(Customer customer, String password);
}
