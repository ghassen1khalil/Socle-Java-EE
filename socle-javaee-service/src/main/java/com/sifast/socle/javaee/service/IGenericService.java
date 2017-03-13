package com.sifast.socle.javaee.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IGenericService<T, P extends Serializable> {

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(Class<?> clazz, P id);

    T findOne(Query query);

    List<T> findMany(Query query);

    List<T> findAll(Class<?> clazz);

    Query createQurey(String query);

}
