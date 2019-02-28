package com.azino.eav.service.impl;

import com.azino.eav.service.BaseService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T, TRepo extends CrudRepository<T, Long>> implements BaseService<T> {

    protected TRepo repository;

    protected BaseServiceImpl(TRepo repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public T update(Long id, T entity) {
        Optional<T> entityDbOptional = repository.findById(id);
        T entityDb = null;
        if(entityDbOptional.isPresent()){
            entityDb = entityDbOptional.get();
            updateDecorator(entityDb, entity);
        }
        return entityDb;
    }

    public abstract void updateDecorator(T entityDb, T entity);

    @Override
    @Transactional
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    @Transactional
    public T findById(Long id) {
        return repository.findById(id).get();
    }
}
