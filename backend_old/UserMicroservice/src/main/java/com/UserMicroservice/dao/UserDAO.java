package com.UserMicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserMicroservice.model.User;

public interface UserDAO extends JpaRepository<User, Integer>{
	
	public User findUserByUsernameAndPassword(String username, String password);
	public User findUserByUsername(String username);

}
