package com.sifast.socle.javaee.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sifast.socle.javaee.dao.IGenericDao;
import com.sifast.socle.javaee.util.UtilDao;

@Repository("genericDao")
public class GenericDao<T, P extends Serializable> extends UtilDao implements IGenericDao<T, P> {

    @Override
    @SuppressWarnings("unchecked")
    public T save(T entity) {
        Session hibernateSession = this.getSession();
        T newEntity = (T) hibernateSession.merge(entity);
        hibernateSession.flush();
        return newEntity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T update(T entity) {
        Session hibernateSession = this.getSession();
        T newEntity = (T) hibernateSession.merge(entity);
        hibernateSession.flush();
        return newEntity;
    }

    @Override
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
        hibernateSession.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findByID(Class<?> clazz, P id) {
        return (T) this.getSession().get(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findOne(Query query) {
        return (T) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findMany(Query query) {
        return query.list();
    }

    @Override
    public Map<P, T> findManyMap(Query query) {
        List<T> list = findMany(query);
        return getConvertListToMap(list);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll(Class<?> clazz) {
        return this.getSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public Map<P, T> findAllMap(Class<?> clazz) {
        List<T> list = findAll(clazz);
        return getConvertListToMap(list);

    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<P, T> getConvertListToMap(List<T> list) {
        Session hibernateSession = this.getSession();
        Map<P, T> map = new HashMap<>();
        for (T obj : list) {
            P pk = (P) hibernateSession.getIdentifier(obj);
            map.put(pk, obj);
        }
        return map;

    }

    @Override
    public Query createQurey(String query) {
        return this.getSession().createQuery(query);
    }

}
