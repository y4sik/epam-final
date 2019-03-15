package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.ProductWithEntitiesDao;
import com.web.securedApp.model.dao.impl.ProductDao;
import com.web.securedApp.model.dao.impl.ProductWithEntitiesDaoImpl;
import com.web.securedApp.model.domain.product.Product;
import com.web.securedApp.service.ProductService;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);
    private GenericDao<Product> productDao = new ProductDao();
    private ProductWithEntitiesDao productWithEntitiesDao = new ProductWithEntitiesDaoImpl();

    @Override
    public List<Product> getAll() {
        List<Product> products = productDao.getAll();
        if (products.isEmpty()) {
            LOGGER.info("List products is null!");
            return null;
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        if (id < 0) {
            return null;
        }
        Product product = productDao.getById(id);
        if (product == null) {
            LOGGER.info("Product is null!");
        }
        return product;
    }

    @Override
    public int insert(Product product) {
        if (product == null) {
            return -1;
        }
        int id = productDao.insert(product);
        if (id == -1) {
            LOGGER.error("Failed to insert product!");
        }
        return id;
    }

    @Override
    public boolean delete(Product product) {
        if (product == null) {
            return false;
        }
        boolean checkDelete = productDao.delete(product);
        if (!checkDelete) {
            LOGGER.info("Failed to delete product!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = productDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete product by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Product product) {
        if (product == null) {
            return false;
        }
        boolean checkUpdate = productDao.update(product);
        if (!checkUpdate) {
            LOGGER.info("Failed to update product");
        }
        return checkUpdate;
    }

    @Override
    public Product getProductWithPictureFeedbackOrderById(Integer id) {
        if (id < 0) {
            return null;
        }
        Product product = productWithEntitiesDao.getProductWithPictureFeedbackOrderById(id);
        if (product == null) {
            LOGGER.info("Product with picture, feedback, order is null!");
        }
        return product;
    }

    @Override
    public List<Product> getAllProductWithPictureFeedbackOrderByCategory(int id) {
        if (id < 0) {
            return null;
        }
        List<Product> products = productWithEntitiesDao.getAllProductWithPictureFeedbackOrderByCategory(id);
        if (products == null) {
            LOGGER.info("Products with picture, feedback, order are null!");
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsWithPictureFeedbackOrder() {
        List<Product> products = productWithEntitiesDao.getAllProductsWithPictureFeedbackOrder();
        if (products.isEmpty()) {
            LOGGER.info("List products with picture, feedback, order is null!");
            return null;
        }
        return products;
    }
}
