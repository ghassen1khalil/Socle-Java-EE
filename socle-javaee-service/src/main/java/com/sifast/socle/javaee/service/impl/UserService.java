package com.sifast.socle.javaee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sifast.socle.javaee.dao.impl.UserDao;
import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IUserService;

@Service("userService")
public class UserService extends GenericService<User, Integer> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByMail(String email) {
        return userDao.findByMail(email);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}