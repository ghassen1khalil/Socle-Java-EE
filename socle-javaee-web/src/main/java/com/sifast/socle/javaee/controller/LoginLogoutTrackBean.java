package com.sifast.socle.javaee.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;

import com.sifast.socle.javaee.entities.LoginLogoutTrack;
import com.sifast.socle.javaee.service.ILoginLogoutTrackService;
import com.sifast.socle.javaee.service.IUserService;

@Controller("loginLogoutTrackBean")
public class LoginLogoutTrackBean implements Serializable, LogoutSuccessHandler, ApplicationListener<AuthenticationSuccessEvent> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient ILoginLogoutTrackService loginLogoutTrackService;

	@Autowired
	private transient IUserService userService;

	private LoginLogoutTrack loginLogoutTrack;

	private List<LoginLogoutTrack> loginLogoutTrackList;

	private List<LoginLogoutTrack> filteredLoginLogoutTrackList;

	public LoginLogoutTrack getLoginLogoutTrack() {
		return loginLogoutTrack;
	}

	public void setLoginLogoutTrack(LoginLogoutTrack loginLogoutTrack) {
		this.loginLogoutTrack = loginLogoutTrack;
	}

	public List<LoginLogoutTrack> getLoginLogoutTrackList() {
		return loginLogoutTrackList;
	}

	public void setLoginLogoutTrackList(List<LoginLogoutTrack> loginLogoutTrackList) {
		this.loginLogoutTrackList = loginLogoutTrackList;
	}

	public List<LoginLogoutTrack> getFilteredLoginLogoutTrackList() {
		return filteredLoginLogoutTrackList;
	}

	public void setFilteredLoginLogoutTrackList(List<LoginLogoutTrack> filteredLoginLogoutTrackList) {
		this.filteredLoginLogoutTrackList = filteredLoginLogoutTrackList;
	}

	@PostConstruct
	public void init() {
		loginLogoutTrackList = loginLogoutTrackService.findAll(LoginLogoutTrack.class);
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
		loginLogoutTrack = new LoginLogoutTrack();
		loginLogoutTrack.setUser(userService.findByLogin(authenticationSuccessEvent.getAuthentication().getName()));
		loginLogoutTrack.setLogonDate(Date.from(Instant.now()));
		WebAuthenticationDetails webDetails = (WebAuthenticationDetails) authenticationSuccessEvent.getAuthentication().getDetails();
		loginLogoutTrack.setIpAddress(webDetails.getRemoteAddress());
		loginLogoutTrack = loginLogoutTrackService.save(loginLogoutTrack);
		init();
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		loginLogoutTrack.setLogoutDate(Date.from(Instant.now()));
		loginLogoutTrackService.update(loginLogoutTrack);
		init();
		response.sendRedirect("views/Login.xhtml");
	}

	public void generateReport() {
		loginLogoutTrackService.generateReport();
	}
}
