package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.impl.CategoryDao;
import com.web.securedApp.model.domain.product.Category;
import com.web.securedApp.service.CategoryService;
import org.apache.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
    private GenericDao<Category> categoryDao = new CategoryDao();

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryDao.getAll();
        if (categories.isEmpty()) {
            LOGGER.info("List categories is null!");
            return null;
        }
        return categories;
    }

    @Override
    public Category getById(int id) {
        if (id < 0) {
            return null;
        }
        Category category = categoryDao.getById(id);
        if (category == null) {
            LOGGER.info("Category is null");
        }
        return category;
    }

    @Override
    public int insert(Category category) {
        if (category == null) {
            return -1;
        }
        int id = categoryDao.insert(category);
        if (id == -1) {
            LOGGER.error("Failed to insert category!");
        }
        return id;
    }

    @Override
    public boolean delete(Category category) {
        if (category == null) {
            return false;
        }
        boolean checkDelete = categoryDao.delete(category);
        if (!checkDelete) {
            LOGGER.info("Failed to delete category!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = categoryDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete category by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Category category) {
        if (category == null) {
            return false;
        }
        boolean checkUpdate = categoryDao.update(category);
        if (!checkUpdate) {
            LOGGER.info("Failed to update category");
        }
        return checkUpdate;
    }
}
