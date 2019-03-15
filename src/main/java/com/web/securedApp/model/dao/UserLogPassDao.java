package com.web.securedApp.model.dao;

import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.model.domain.customer.User;

public interface UserLogPassDao {
    User getByLogPass(String login, String password);
}
