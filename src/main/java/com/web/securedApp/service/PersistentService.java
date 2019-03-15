package com.web.securedApp.service;

import com.web.securedApp.model.domain.Entity;

import java.util.List;

public interface PersistentService<T extends Entity> {
    List<T> getAll();

    T getById(int id);

    int insert(T entity);

    boolean delete(T entity);

    boolean delete(int id);

    boolean update(T entity);
}
