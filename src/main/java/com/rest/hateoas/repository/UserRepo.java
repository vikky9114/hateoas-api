package com.rest.hateoas.repository;

import org.springframework.data.repository.CrudRepository;

import com.rest.hateoas.entity.User;

public interface UserRepo extends CrudRepository<User, Integer> {

}
