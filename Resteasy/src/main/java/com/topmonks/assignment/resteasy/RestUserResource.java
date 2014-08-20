package com.topmonks.assignment.resteasy;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.topmonks.assignment.mybatis.UserService;
import com.topmonks.assignment.users.User;

@Named
@Path("/users")
public class RestUserResource {

	@Inject
	private UserService userService;

	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUsersXML() {
		List<User> users = userService.getAllUsers();
		GenericEntity<List<User>> ge = new GenericEntity<List<User>>(users) {
		};
		return Response.ok(ge).build();
	}

	@Path("/update")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Response updateUser(@QueryParam("id") Integer id,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("email") String email) {
		// validate id
		if (id == null) {
			return Response.ok().entity("Id is mandatory").build();
		}
		// validate first name
		if (firstName == null || firstName.isEmpty()) {
			return Response.ok().entity("First name is mandatory").build();
		}
		// validate last name
		if (lastName == null || lastName.isEmpty()) {
			return Response.ok().entity("Last name is mandatory").build();
		}
		if (email == null || email.isEmpty()) {
			return Response.ok().entity("Email name is mandatory").build();
		}
		// Add user and return the response
		userService.updateUser(new User(id, firstName, lastName, email));
		return Response.ok().entity("User " + firstName + " updated").build();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserXMLById(@PathParam("id") Integer id) {
		return Response.ok(userService.getUserById(id)).build();
	}

	@Path("/")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Response saveUser(@QueryParam("id") Integer id,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("email") String email) {
		// validate id
		if (id == null) {
			return Response.ok().entity("Id is mandatory").build();
		}
		// validate first name
		if (firstName == null || firstName.isEmpty()) {
			return Response.ok().entity("First name is mandatory").build();
		}
		// validate last name
		if (lastName == null || lastName.isEmpty()) {
			return Response.ok().entity("Last name is mandatory").build();
		}
		// validate email
		if (email == null || email.isEmpty()) {
			return Response.ok().entity("Email name is mandatory").build();
		}
		// Add user and return the response
		userService.insertUser(new User(id, firstName, lastName, email));
		return Response.ok().entity("User " + firstName + " added").build();
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteUser(@PathParam("id") Integer id) {
		userService.deleteUser(id);
		return Response.ok("<status>success</status>").build();
	}
}
