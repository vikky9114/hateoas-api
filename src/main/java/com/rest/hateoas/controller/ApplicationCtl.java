package com.rest.hateoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rest.hateoas.entity.User;
import com.rest.hateoas.service.UserService;

@Controller
public class ApplicationCtl {

	@Autowired
	UserService service;

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}


}
