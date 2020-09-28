package com.rest.hateoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.hateoas.entity.User;
import com.rest.hateoas.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User createUser(User user) {
		if (user != null)
			return userRepository.save(user);
		return null;
	}

	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public Boolean deleteUserById(Integer id) {
		userRepository.deleteById(id);
		return true;
	}

	public Boolean updateUser(Integer id, String name, Integer age) {
		return (userRepository.setUserInfoById(name, age, id) > 0) ? true : false;
	}
}
