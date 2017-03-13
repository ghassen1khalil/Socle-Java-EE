package com.sifast.socle.javaee.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sifast.socle.javaee.dao.IUserDao;
import com.sifast.socle.javaee.entities.User;

@Repository("userDao")
public class UserDao extends GenericDao<User, Integer> implements IUserDao {

    @Override
    public User findByMail(String email) {
        Query query = getSession().getNamedQuery("findByMail").setString("email", email);
        return findOne(query);
    }

    @Override
    public User findByLogin(String login) {
        Query query = getSession().getNamedQuery("findByLogin").setString("login", login);
        return findOne(query);
    }
}