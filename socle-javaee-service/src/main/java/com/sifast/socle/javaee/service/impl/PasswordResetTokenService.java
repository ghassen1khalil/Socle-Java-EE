package com.sifast.socle.javaee.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sifast.socle.javaee.dao.impl.PasswordResetTokenDao;
import com.sifast.socle.javaee.entities.PasswordResetToken;
import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IPasswordResetTokenService;
import com.sifast.socle.javaee.service.IUserService;

@Service("passwordResetTokenService")
public class PasswordResetTokenService extends GenericService<PasswordResetToken, Integer> implements IPasswordResetTokenService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetTokenService.class);

	private String password = "password_reset_token.properties";

	private String expirationInMinutes = "EXPIRATION_IN_MINUTES";

	@Autowired
	private PasswordResetTokenDao passwordresettokenDao;

	@Autowired
	private IUserService userService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment environment;

	@Override
	public PasswordResetToken findTokenByToken(String token) {
		return passwordresettokenDao.findByToken(token);
	}

	@Override
	public boolean resetPassword(String mail) {
		boolean resetPasswordDone = false;
		User user = userService.findByMail(mail);
		if (user != null) {
			SimpleMailMessage email = buildEmail(user);
			mailSender.send(email);
			resetPasswordDone = true;
		}
		return resetPasswordDone;
	}

	private SimpleMailMessage buildEmail(User user) {
		String token = createToken(user);
		String appUrl = buildAppUrl(user, token);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject(resourceBundle.getString("EMAIL_SUBJECT"));
		email.setText(resourceBundle.getString("EMAIL_MESSAGE") + " \r\n" + appUrl);
		email.setFrom(environment.getProperty("support.email"));
		return email;
	}

	private String buildAppUrl(User user, String token) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return new StringBuilder().append("http://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath())
				.append("/user/resetPassword?id=").append(user.getId()).append("&token=").append(token).toString();
	}

	private String createToken(User user) {
		String token = UUID.randomUUID().toString();
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(password);
		try {
			properties.load(inputStream);
		} catch (IOException exception) {
			LOGGER.error("Create token exception : " + exception);
		}

		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, Integer.valueOf(properties.getProperty(expirationInMinutes)));
		passwordresettokenDao.save(passwordResetToken);
		return token;
	}

}