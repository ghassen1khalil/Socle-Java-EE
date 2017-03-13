package com.sifast.socle.javaee.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sifast.socle.javaee.entities.Role;
import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IRoleService;
import com.sifast.socle.javaee.service.IUserService;
import com.sifast.socle.javaee.utils.Constants;
import com.sifast.socle.javaee.utils.MessageUtils;
import com.sifast.socle.javaee.utils.SocleError;

@Controller("userBean")
@Scope("session")
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserBean.class);

	@Autowired
	private transient IUserService userService;

	@Autowired
	private transient IRoleService roleService;

	private User user = new User();

	private List<User> usersList;

	private List<Role> selectedRolesList = new ArrayList<>();

	private List<User> filteredUsersList;

	private String oldPassword;

	private String newPassword;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public List<User> getFilteredUsersList() {
		return filteredUsersList;
	}

	public void setFilteredUsersList(List<User> filteredUsersList) {
		this.filteredUsersList = filteredUsersList;
	}

	public List<Role> getSelectedRolesCheckboxMenu() {
		return selectedRolesList;
	}

	public void setSelectedRolesCheckboxMenu(List<Role> selectedRolesCheckboxMenu) {
		this.selectedRolesList = selectedRolesCheckboxMenu;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public List<Role> getSelectedRolesList() {
		return selectedRolesList;
	}

	public void setSelectedRolesList(List<Role> selectedRolesList) {
		this.selectedRolesList = selectedRolesList;
	}

	@PostConstruct
	public void init() {
		usersList = userService.findAll(User.class);
		selectedRolesList.clear();
	}

	public String preCreate() {
		user = new User();
		selectedRolesList.clear();
		return Constants.CREATE;
	}

	public String preEdit() {
		selectedRolesList.clear();
		selectedRolesList.addAll(user.getRoles());
		return Constants.EDIT;
	}

	public void delete() {
		if (user.getId() == 1) {
			SocleError.showError(MessageUtils.getMessage("CannotDeleteUser"));
		} else {
			userService.delete(user);
			LOGGER.debug("User deleted " + user.toString());
			init();
		}
	}

	public void save() {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getLogin()));
		user.getRoles().clear();
		user.getRoles().addAll(selectedRolesList);
		user = userService.save(user);
		LOGGER.debug("User saved " + user.toString());
		init();
	}

	public void update() {
		user.getRoles().clear();
		user.getRoles().addAll(selectedRolesList);
		user = userService.update(user);
		LOGGER.debug("User updated " + user.toString());
		init();
	}

	public void reset() {
		user = new User();
	}

	public String cancel() {
		init();
		return Constants.LIST;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updatePassword() throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		user = userService.findByLogin(authentication.getName());
		if (!isCorrectOldPassword(encoder)) {
			SocleError.showError(MessageUtils.getMessage("IncorrectPassword"));
		} else {
			user.setPassword(encoder.encodePassword(newPassword, user.getLogin()));
			userService.update(user);
			return Constants.LOGIN;
		}
		return null;
	}

	private boolean isCorrectOldPassword(ShaPasswordEncoder encoder) {
		return user.getPassword().equals(encoder.encodePassword(oldPassword, user.getLogin()));
	}
}
