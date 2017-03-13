package com.sifast.socle.javaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sifast.socle.javaee.entities.Role;
import com.sifast.socle.javaee.service.IRoleService;
import com.sifast.socle.javaee.utils.MessageUtils;
import com.sifast.socle.javaee.utils.SocleError;

@Controller("roleBean")
@Scope("session")
public class RoleBean implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleBean.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient IRoleService roleService;

	private Role role = new Role();

	private List<Role> rolesList;

	private List<Role> filteredRolesList;

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public List<Role> getFilteredRolesList() {
		return filteredRolesList;
	}

	public void setFilteredRolesList(List<Role> filteredRolesList) {
		this.filteredRolesList = filteredRolesList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@PostConstruct
	public void init() {
		rolesList = roleService.findAll(Role.class);
	}

	public void preCreate() {
		role = new Role();
	}

	public void delete() {
		if (role.getId() == 1) {
			SocleError.showError(MessageUtils.getMessage("CannotDeleteRole"));
		} else {
			roleService.delete(role);
			LOGGER.debug("Role deleted " + role.toString());
			init();
		}
	}

	public void save() {
		role = roleService.save(role);
		LOGGER.debug("Role saved " + role.toString());
		init();
	}

	public void update() {
		role = roleService.update(role);
		LOGGER.debug("Role updated " + role.toString());
		init();
	}
}
