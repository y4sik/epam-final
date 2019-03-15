package com.web.securedApp.service;

import com.web.securedApp.model.domain.customer.Customer;

import java.util.List;

public interface CustomerService extends PersistentService<Customer>{
    Customer getCustomerWithOrderFeedbackById(int id);
}
