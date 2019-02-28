package com.azino.eav.service;

import java.util.List;

public interface BaseService<T> {

    T save(T entity);

    void delete(T entity);

    void deleteById(Long id);

    T update(Long id, T entity);

    List<T> findAll();

    T findById(Long id);

}
