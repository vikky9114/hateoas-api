package com.rest.hateoas.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.rest.hateoas.controller.UserController;
import com.rest.hateoas.entity.User;
import com.rest.hateoas.model.UserModel;

/**
 * @author
 *
 */
@Component
public class UserAssemler extends RepresentationModelAssemblerSupport<User, UserModel> {

	public UserAssemler() {
		super(UserController.class, UserModel.class);
	}

	/**
	 * Converts the given entity into a D, which extends RepresentationModel.
	 */
	@Override
	public UserModel toModel(User entity) {
		UserModel model = instantiateModel(entity);
		model.setId(entity.getId());
		model.setAge(entity.getAge());
		model.setName(entity.getName());
		model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserById(entity.getId()))
				.withSelfRel().withTitle("Get User").withType(RequestMethod.GET.name()));
		model.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).deleteUserById(entity.getId()))
				.withRel("Delete").withType(RequestMethod.DELETE.name()));
		model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).updateUser(entity))
				.withRel("Update").withType(RequestMethod.PUT.name()));
		return model;
	}

	/**
	 *
	 * Converts an Iterable or Ts into an Iterable of RepresentationModel and wraps
	 * them in a CollectionModel instance.
	 * 
	 */
	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
		return super.toCollectionModel(entities);
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
}
