package com.sifast.socle.javaee.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public interface IGenericDao<T, P extends Serializable> {
   
	T save(T entity);

    T update(T entity);

    void delete(T entity);

    T findByID(Class<?> clazz, P id);

    T findOne(Query query);

    List<T> findMany(Query query);

    Map<P, T> findManyMap(Query query);

    List<T> findAll(Class<?> clazz);

    Map<P, T> findAllMap(Class<?> clazz);

    Map<P, T> getConvertListToMap(List<T> list);

    Query createQurey(String query);
}