package com.topmonks.assignment.userServiceTests;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.topmonks.assignment.users.User;
import com.topmonks.assignment.mybatis.UserService;

public class UserServiceTests {
	private static UserService userService;

	@BeforeClass
	public static void setup() {
		userService = new UserService();
		userService.createTableUser();
	}

	@AfterClass
	public static void teardown() {
		userService.dropTableUser();
		userService = null;
		
	}

	@Test
	public void testGetUserById() {
		User user = new User();
		user.setUserId(1);
		user.setEmailId("test_email_" + System.currentTimeMillis()
				+ "@gmail.com");
		user.setFirstName("TestFirstName");	
		user.setLastName("TestLastName");

		userService.insertUser(user);
		
		user = userService.getUserById(1);
		Assert.assertNotNull(user);
		System.out.println(user);
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = userService.getAllUsers();
		Assert.assertNotNull(users);
		for (User user : users) {
			System.out.println(user);
		}

	}

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setUserId(10);
		user.setEmailId("test_email_" + System.currentTimeMillis()
				+ "@gmail.com");
		user.setFirstName("TestFirstName");	
		user.setLastName("TestLastName");

		userService.insertUser(user);
		Assert.assertTrue(user.getUserId() != 0);
		User createdUser = userService.getUserById(user.getUserId());
		Assert.assertNotNull(createdUser);
		Assert.assertEquals(user.getEmailId(), createdUser.getEmailId());
		Assert.assertEquals(user.getFirstName(), createdUser.getFirstName());
		Assert.assertEquals(user.getLastName(), createdUser.getLastName());

	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setUserId(2);
		user.setEmailId("test_email_" + System.currentTimeMillis()
				+ "@gmail.com");
		user.setFirstName("TestFirstName");	
		user.setLastName("TestLastName");
		userService.insertUser(user);
		
		long timestamp = System.currentTimeMillis();
		user = userService.getUserById(2);
		user.setFirstName("TestFirstName" + timestamp);
		user.setLastName("TestLastName" + timestamp);
		user.setEmailId("TestEmailId" + timestamp);
		userService.updateUser(user);
		User updatedUser = userService.getUserById(2);
		Assert.assertEquals(user.getFirstName(), updatedUser.getFirstName());
		Assert.assertEquals(user.getLastName(), updatedUser.getLastName());
	}

	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setUserId(14);
		user.setEmailId("test_email_" + System.currentTimeMillis()
				+ "@gmail.com");
		user.setFirstName("TestFirstName");	
		user.setLastName("TestLastName");
		userService.insertUser(user);
		
		userService.deleteUser(user.getUserId());
		User deletedUser = userService.getUserById(4);
		Assert.assertNull(deletedUser);
	}
}
