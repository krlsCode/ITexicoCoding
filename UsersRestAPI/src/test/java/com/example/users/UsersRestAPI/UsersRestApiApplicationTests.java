package com.example.users.UsersRestAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.UsersRestApiApplication;
import com.example.model.User;

import ch.qos.logback.core.net.SyslogOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersRestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersRestApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}
	
	@Test
	public void testGetAllActiveUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users/get_all_users",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetUserById() {
		User user = restTemplate.getForObject(getRootUrl() + "/users/get_user/1", User.class);
		assertNotNull(user);
	}
	
	@Test
	public void testCreateUser() throws ParseException {
		@SuppressWarnings("deprecation")
		Date d1 = new Date(2000, 11, 21);
		User user = new User();
		user.setFirstName("admin");
		user.setLastName("admin");
		user.setDateOfBirth(d1);

		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users/create_user", user, User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testUpdateUser() {
		int id = 1;
		@SuppressWarnings("deprecation")
		Date d1 = new Date(2000, 11, 21);
		User user = restTemplate.getForObject(getRootUrl() + "/users/get_user/" + id, User.class);
		user.setFirstName("admin1");
		user.setLastName("admin2");
		user.setDateOfBirth(d1);

		restTemplate.put(getRootUrl() + "/users/update_user/" + id, user);

		User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/get_user/" + id, User.class);
		assertNotNull(updatedUser);
	}
	
	@Test
	public void testDeleteUser() {
		int id = 10;
		@SuppressWarnings("deprecation")
		Date d1 = new Date(2000, 11, 21);
		User user = restTemplate.getForObject(getRootUrl() + "/users/get_user/" + id, User.class);
		user.setFirstName("admin1");
		user.setLastName("admin2");
		user.setDateOfBirth(d1);
		
		restTemplate.put(getRootUrl() + "/users/delete_user/" + id, user);
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/delete_user/" + id, User.class);
		assertNotNull(updatedUser);
	}

}
