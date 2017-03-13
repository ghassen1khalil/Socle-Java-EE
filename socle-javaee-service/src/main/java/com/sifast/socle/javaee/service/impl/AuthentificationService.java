package com.sifast.socle.javaee.service.impl;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sifast.socle.javaee.service.IAuthentificationService;

@Service("authentificationService")
public class AuthentificationService implements IAuthentificationService {

	public static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationService.class);

	@Override
	public void doLogin() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
		try {
			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		} catch (ServletException e) {
			LOGGER.error("ServletException " + e);
		} catch (IOException e) {
			LOGGER.error("IOException " + e);
		}
		FacesContext.getCurrentInstance().responseComplete();
	}
}
