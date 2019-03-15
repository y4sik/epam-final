package com.web.securedApp.model.dao;

import com.web.securedApp.model.domain.customer.Customer;

public interface CustomerWithEntitiesDao {
    Customer getCustomerWithOrderFeedbackById(int id);
}
