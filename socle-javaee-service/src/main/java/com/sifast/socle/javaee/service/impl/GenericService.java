package com.sifast.socle.javaee.service.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sifast.socle.javaee.dao.IGenericDao;
import com.sifast.socle.javaee.service.IGenericService;

public class GenericService<T, P extends Serializable> implements IGenericService<T, P> {

    @Autowired
    @Qualifier("genericDao")
    private IGenericDao<T, P> genericDao;

    @Override
    public T save(T entity) {
        return genericDao.save(entity);
    }

    @Override
    public T update(T entity) {
        return genericDao.update(entity);
    }

    @Override
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    @Override
    public T findById(Class<?> clazz, P id) {
        return genericDao.findByID(clazz, id);
    }

    @Override
    public T findOne(Query query) {
        return genericDao.findOne(query);
    }

    @Override
    public List<T> findMany(Query query) {
        return genericDao.findMany(query);
    }

    @Override
    public List<T> findAll(Class<?> clazz) {
        return genericDao.findAll(clazz);
    }

    @Override
    public Query createQurey(String query) {
        return genericDao.createQurey(query);
    }

}
