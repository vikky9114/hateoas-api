package com.rest.hateoas;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rest.hateoas.controller.ApplicationCtl;
import com.rest.hateoas.controller.UserController;
import com.rest.hateoas.entity.User;
import com.rest.hateoas.model.UserModel;
import com.rest.hateoas.service.UserService;
import com.rest.hateoas.util.UserAssemler;

@TestInstance(Lifecycle.PER_CLASS)
public class TestUser extends HateoasApplicationTests {

	@MockBean
	private UserController userCtl;
	@MockBean
	private UserAssemler userAssemler;

	@MockBean
	private UserService service;

	private MockMvc mockMvc;

	private User user =new User(57, "pawan", 27);
	private List<User> users = Arrays.asList(user);
	private UserModel userModel=new UserModel(57, "pawan", 27);
	private List<UserModel> usermodels = Arrays.asList();

	@BeforeAll
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userCtl).build();
	}

	/*
	 * @Test public void testGetUsers() throws Exception {
	 * when(service.getUsers()).thenReturn(users);
	 * mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(
	 * status().isOk()); }
	 */

	@Test
	public void testGetUser() throws Exception {
		when(service.getUsers()).thenReturn(null);
		when(userAssemler.toModel(user)).thenReturn(null);
		mockMvc.perform(get("/hapi/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	
}
