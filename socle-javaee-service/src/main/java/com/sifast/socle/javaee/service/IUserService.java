package com.sifast.socle.javaee.service;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.User;

@Transactional
public interface IUserService extends IGenericService<User, Integer> {

	User findByMail(String email);

	User findByLogin(String login);
}