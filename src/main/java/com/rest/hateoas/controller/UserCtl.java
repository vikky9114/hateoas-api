package com.rest.hateoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.hateoas.model.UserModel;
import com.rest.hateoas.service.UserService;
import com.rest.hateoas.util.UserAssemler;

@RestController
@RequestMapping("/hapi/")
public class UserCtl {

	@Autowired
	UserService service;
	@Autowired
	UserAssemler userAssemler;
	
	@GetMapping("users")
	public List<UserModel> getUsers(){
		System.out.println("i m working");
		return userAssemler.toList(service.getUsers()); 
	}
	
}
