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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sifast.socle.javaee.entities.User;
import com.sifast.socle.javaee.service.IUserService;
import com.sifast.socle.javaee.utils.Constants;

@Path("/users")
@Service("userResource")
public class UserResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private IUserService userService;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response findAll() {
		List<User> usersList = userService.findAll(User.class);
		return Response.status(Constants.STATUS_OK).entity(usersList).build();
	}

	@GET
	@Path("/findUserById")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response findUserById(@QueryParam("id") int id) {
		User user = userService.findById(User.class, id);
		return Response.status(Constants.STATUS_OK).entity(user).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/save")
	public Response save(@QueryParam("userJson") String userJson) {
		Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create();
		User user = gson.fromJson(userJson, User.class);
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getEmail()));
		user = userService.save(user);
		LOGGER.debug("INFO level message: User saved " + user.toString());
		return Response.status(Constants.STATUS_CREATED).entity(user).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/save")
	public Response update(@QueryParam("userJson") String userJson) {
		Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create();
		User user = gson.fromJson(userJson, User.class);
		ShaPasswordEncoder encoder = new ShaPasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getEmail()));
		user = userService.update(user);
		LOGGER.debug("INFO level message: User updated " + user.toString());
		return Response.status(Constants.STATUS_CREATED).entity(user).build();
	}

	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@QueryParam("userJson") String userJson) {
		Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create();
		User user = gson.fromJson(userJson, User.class);
		userService.delete(user);
		LOGGER.debug("INFO level message: User deleted " + user.toString());
		return Response.status(Constants.STATUS_ACCEPTED).entity("User deleted successfully !!").build();
	}
}
