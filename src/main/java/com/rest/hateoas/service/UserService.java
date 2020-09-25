package com.rest.hateoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.hateoas.entity.User;
import com.rest.hateoas.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo repo;

	public List<User> getUsers() {
		return (List<User>) repo.findAll();
	}

	public User getUserById(Integer id) {
		return repo.findById(id).orElse(null);
	}

}
