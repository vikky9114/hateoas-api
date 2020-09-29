package com.rest.hateoas.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Component;

import com.rest.hateoas.controller.UserController;
import com.rest.hateoas.entity.User;
import com.rest.hateoas.model.UserModel;

/**
 * @author
 *
 */
@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

	/**
	 * Creates a new UserAssembler using the given controller class and resource
	 * type.
	 */
	public UserAssembler() {
		super(UserController.class, UserModel.class);
	}

	/**
	 * Converts the given entity into a DTO, which extends RepresentationModel.
	 */
	@Override
	public UserModel toModel(User entity) {
	
		//Instantiates the resource object/Model Object.
		UserModel userModel = instantiateModel(entity);
		
		userModel.setId(entity.getId());
		userModel.setAge(entity.getAge());
		userModel.setName(entity.getName());
		
		userModel.add(linkTo(methodOn(UserController.class).getUserById(entity.getId()))
				.withSelfRel().withTitle("Get User").withType(RequestMethod.GET.name()));
		userModel.add(
				linkTo(methodOn(UserController.class).deleteUserById(entity.getId()))
				.withRel("Delete").withType(RequestMethod.DELETE.name()));
		userModel.add(linkTo(methodOn(UserController.class).updateUser(entity))
				.withRel("Update").withType(RequestMethod.PUT.name()));
	
		/*
		 * userModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
		 * UserController.class).updateUser(entity))
		 * .withRel("Update").withType(RequestMethod.PUT.name()));
		 */
		
		return userModel;
	}

	/**
	 * 
	 * Convert list of entity into List of model
	 * 
	 * @param entities
	 * @return
	 */
	public List<UserModel> toList(List<User> entities) {
		return entities.stream().map(entity -> toModel(entity)).collect(Collectors.toList());
	}

	/**
	 *
	 * Converts an Iterable into an Iterable of RepresentationModel and wraps them
	 * in a CollectionModel instance.
	 * 
	 * @Override public CollectionModel<UserModel> toCollectionModel(Iterable<?
	 *           extends User> entities) { return super.toCollectionModel(entities);
	 *           }
	 */

}
