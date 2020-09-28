package com.rest.hateoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.hateoas.entity.User;
import com.rest.hateoas.model.UserModel;
import com.rest.hateoas.service.UserService;
import com.rest.hateoas.util.UserAssemler;

/**
 * @author 
 *
 */
@RestController
@RequestMapping("/hapi/")
public class UserCtl {

	@Autowired
	UserService service;
	
	@Autowired
	UserAssemler userAssemler;

	/**
	 * @return
	 */
	@GetMapping("users")
	public ResponseEntity<List<UserModel>> getUsers() {
		List<User> entities = service.getUsers();
		return (entities != null) ? new ResponseEntity<List<UserModel>>(userAssemler.toList(entities), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("user/{id}")
	public ResponseEntity<UserModel> getUser(@PathVariable("id") Integer id) {
		User entity = service.getUserById(id);
		return (entity != null) ? new ResponseEntity<>(userAssemler.toModel(entity), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * @param id
	 * @return
	 */
	@DeleteMapping("remove/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
		return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
	}
	
	/**
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception e){
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
