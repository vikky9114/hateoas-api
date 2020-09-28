package com.rest.hateoas.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rest.hateoas.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Modifying
	@Transactional
	@Query("update User u set u.name = ?1, u.age = ?2 where u.id = ?3")
	public Integer setUserInfoById(String name, Integer age, Integer id);
}
