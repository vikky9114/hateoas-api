package com.rest.hateoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.hateoas.entity.User;
import com.rest.hateoas.service.UserService;

@RestController
@RequestMapping("/hapi/")
public class UserCtl {

	@Autowired
	UserService service;
	
	@GetMapping("users")
	public List<User> getUsers(){
		System.out.println("i m working");
		return service.getUsers();
	}
	
}
