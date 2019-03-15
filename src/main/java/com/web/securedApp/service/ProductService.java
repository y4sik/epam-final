package com.web.securedApp.service;

import com.web.securedApp.model.domain.customer.User;
import com.web.securedApp.model.domain.product.Product;

import java.util.List;

public interface ProductService extends PersistentService<Product>{
    Product getProductWithPictureFeedbackOrderById(Integer id);
    List<Product> getAllProductWithPictureFeedbackOrderByCategory(int id);
    List<Product> getAllProductsWithPictureFeedbackOrder();

}
