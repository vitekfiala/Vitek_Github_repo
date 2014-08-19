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
	public Response updateUser(User user) {
		userService.updateUser(user);		
		return Response.ok("<status>success</status>").build();
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
	public Response saveUser(User user) {
		userService.insertUser(user);
		return Response.ok("<status>success</status>").build();
	}

	@Path("/{id}")
	@DELETE
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Response deleteUser(@PathParam("id") Integer id) {
		userService.deleteUser(id);		
		return Response.ok("<status>success</status>").build();
	}
}
