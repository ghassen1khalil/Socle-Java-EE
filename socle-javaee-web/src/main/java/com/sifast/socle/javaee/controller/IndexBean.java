package com.sifast.socle.javaee.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IUserService;

@Controller("indexBean")
@Scope("session")
public class IndexBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient IUserService userService;

	private User user = new User();

	private String password;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@PostConstruct
	public void init() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findByLogin(authentication.getName());
	}

	public void changePassword() throws IOException {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		user.setPassword(encoder.encodePassword(password, user.getLogin()));
		userService.update(user);
	}
}
