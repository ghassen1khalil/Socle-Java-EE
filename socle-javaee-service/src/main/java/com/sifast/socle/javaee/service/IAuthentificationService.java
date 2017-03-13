package com.sifast.socle.javaee.service;

import org.springframework.transaction.annotation.Transactional;

@FunctionalInterface
@Transactional
public interface IAuthentificationService {

	void doLogin();
}
