package com.sifast.socle.javaee.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sifast.socle.javaee.entities.PasswordResetToken;
import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IAuthentificationService;
import com.sifast.socle.javaee.service.IPasswordResetTokenService;
import com.sifast.socle.javaee.service.IUserService;
import com.sifast.socle.javaee.utils.MessageUtils;
import com.sifast.socle.javaee.utils.SocleError;

@Controller("authentificationBean")
@Scope("session")
public class AuthentificationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient IAuthentificationService authentificationService;

	@Autowired
	private transient IUserService userService;

	@Autowired
	private transient IPasswordResetTokenService passwordResetTokenService;

	private String email;

	private String password;

	private User user = new User();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void doLogin() {
		authentificationService.doLogin();
	}

	public boolean resetPassword() {
		boolean isPasswordReseted = passwordResetTokenService.resetPassword(email);
		if (!isPasswordReseted) {
			SocleError.showError(MessageUtils.getMessage("EmailNotRegistered"));
		} else {
			SocleError.showInfo(MessageUtils.getMessage("TokenSent"));
		}
		return isPasswordReseted;
	}

	public String createNewPassword() throws IOException {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		user.setPassword(encoder.encodePassword(password, user.getLogin()));
		userService.update(user);
		return "Login";
	}

	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
	public String showResetPasswordPage(Locale locale, Model model, @RequestParam("id") int id, @RequestParam("token") String token) {
		PasswordResetToken passwordResetToken = passwordResetTokenService.findTokenByToken(token);
		user = passwordResetToken.getUser();
		if (!(isTokenValid(id, token, passwordResetToken))) {
			String message = "auth.message.invalidToken";
			model.addAttribute("message", message);
			return "redirect:/views/Login.xhtml";
		}
		if (isTokenExpired(passwordResetToken)) {
			model.addAttribute("message", "auth.message.expired");
			return "redirect:/views/Login.xhtml";
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/views/CreateNewPassword.xhtml";
	}

	private boolean isTokenValid(int id, String token, PasswordResetToken passwordResetToken) {
		return passwordResetToken != null && token.equals(passwordResetToken.getToken()) && user.getId() == id;
	}

	private boolean isTokenExpired(PasswordResetToken passwordResetToken) {
		return LocalDateTime.now().isAfter(passwordResetToken.getExpirationLocalDateTime());
	}
}