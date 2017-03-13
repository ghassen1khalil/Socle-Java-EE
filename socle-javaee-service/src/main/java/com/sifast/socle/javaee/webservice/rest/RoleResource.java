package com.sifast.socle.javaee.webservice.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sifast.socle.javaee.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sifast.socle.javaee.entities.Role;
import com.sifast.socle.javaee.service.IRoleService;

@Path("/roles")
@Service("roleResource")
public class RoleResource {

	public static final Logger LOGGER = LoggerFactory.getLogger(RoleResource.class);

	@Autowired
	private IRoleService roleService;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response findAll() {
		List<Role> rolesList = roleService.findAll(Role.class);
		return Response.status(Constants.STATUS_OK).entity(rolesList).build();
	}

	@GET
	@Path("/findRoleByDesignation")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response findRoleByDesignation(@QueryParam("roleDesignation") String roleDesignation) {
		Role role = roleService.findRoleByDesignation(roleDesignation);
		return Response.status(Constants.STATUS_OK).entity(role).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/save")
	public Response save(@QueryParam("roleJson") String roleJson) {
		Role role = new Gson().fromJson(roleJson, Role.class);
		role = roleService.save(role);
		LOGGER.debug("INFO level message: Role saved " + role.toString());
		return Response.status(Constants.STATUS_CREATED).entity(role).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/update")
	public Response update(@QueryParam("roleJson") String roleJson) {
		Role role = new Gson().fromJson(roleJson, Role.class);
		role = roleService.update(role);
		LOGGER.debug("INFO level message: Role updated " + role.toString());
		return Response.status(Constants.STATUS_CREATED).entity(role).build();
	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response delete(@QueryParam("roleJson") String roleJson) {
		Role role = new Gson().fromJson(roleJson, Role.class);
		roleService.delete(role);
		LOGGER.debug("INFO level message: Role deleted " + role.toString());
		return Response.status(Constants.STATUS_ACCEPTED).entity("Role deleted successfully !!").build();
	}
}
