package com.rest.hateoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.hateoas.entity.User;
import com.rest.hateoas.model.UserModel;
import com.rest.hateoas.service.UserService;
import com.rest.hateoas.util.UserAssembler;

/**
 * @author
 *
 */
@RestController
@RequestMapping("/v1/hateoas")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserAssembler userAssemler;
	
	/**
	 * @param user
	 * @return
	 */
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody User user){
		userService.createUser(user);
		return new ResponseEntity<String>("User created successfuly",HttpStatus.CREATED);
	}
	

	/**
	 * 
	 * 
	 * @return
	 */
	@GetMapping("/user")
	public ResponseEntity<List<UserModel>> getUsers() {
		List<User> users = userService.getUsers();
		return (users != null) ? new ResponseEntity<List<UserModel>>(userAssemler.toList(users), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		return (user != null) ? new ResponseEntity<>(userAssemler.toModel(user), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteUserById(id);
		return new ResponseEntity<>("User deleted successfuly", HttpStatus.OK);
	}

	/**
	 * @param user
	 * @return
	 */
	@PutMapping("/user")
	public ResponseEntity<Boolean> updateUser(@RequestBody User user) {
		if (userService.updateUser(user.getId(), user.getName(), user.getAge()))
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}

	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
