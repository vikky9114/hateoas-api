package com.rest.hateoas.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.rest.hateoas.controller.UserCtl;
import com.rest.hateoas.entity.User;
import com.rest.hateoas.model.UserModel;

@Component
public class UserAssemler extends RepresentationModelAssemblerSupport<User, UserModel>{

	public UserAssemler() {
		super(User.class,UserModel.class);
	}

	@Override
	public UserModel toModel(User entity) {
		UserModel model=instantiateModel(entity);
		model.setId(entity.getId());
		model.setAge(entity.getAge());
		model.setName(entity.getName());
		model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserCtl.class).getUsers()).slash(model.getId()).withSelfRel());
		return model;
	}

	@Override
	public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
		entities.forEach(entity->new UserAssemler().toModel(entity));
		return super.toCollectionModel(entities);
	}
	
	public List<UserModel> toList(List<User> entities){
		List<UserModel> list= new ArrayList<UserModel>();
		for (User user : entities) {
			list.add(toModel(user));
		}
	return list;
	}
}
