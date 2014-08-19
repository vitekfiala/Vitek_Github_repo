package com.topmonks.assignment.RestUserResourceTests;

import java.util.List;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.junit.Assert;
import org.junit.Test;

import com.topmonks.assignment.users.User;

public class UserResourceTest {

	static final String ROOT_URL = "http://localhost/Resteasy/rest/";

	@Test
	public void testGetUsers() throws Exception {
		ClientRequest request = new ClientRequest(ROOT_URL + "users/");
		ClientResponse<List<User>> response = request
				.get(new GenericType<List<User>>() {
				});
		List<User> users = response.getEntity();
		Assert.assertNotNull(users);
	}

	@Test
	public void testGetUserById() throws Exception {
		ClientRequest request = new ClientRequest(ROOT_URL + "users/1");
		ClientResponse<User> response = request.get(User.class);
		User user = response.getEntity();
		Assert.assertNotNull(user);
	}

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setUserId(3);
		user.setFirstName("User3");
		user.setLastName("User last");
		user.setEmailId("user3@gmail.com");
		ClientRequest request = new ClientRequest(ROOT_URL + "users/");
		request.body("application/xml", user);
		ClientResponse<String> response = request.post(String.class);
		String statusXML = response.getEntity();
		Assert.assertNotNull(statusXML);
	}

	@Test
	public void testDeleteUser() throws Exception {
		ClientRequest request = new ClientRequest(ROOT_URL + "users/2");
		ClientResponse<String> response = request.delete(String.class);
		String statusXML = response.getEntity();
		Assert.assertNotNull(statusXML);
	}
}
