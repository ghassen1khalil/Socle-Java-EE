package com.sifast.socle.javaee.dao;

import com.sifast.socle.javaee.entities.User;

public interface IUserDao extends IGenericDao<User, Integer> {

    User findByMail(String email);

    User findByLogin(String login);
}