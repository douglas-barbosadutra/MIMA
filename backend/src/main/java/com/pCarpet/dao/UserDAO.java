package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.Machine;
import com.pCarpet.model.User;


public interface UserDAO extends CrudRepository<User, Integer>{
	public User findUserByUsernameAndPassword(String username, String password);
	public User findUserById(int id);
	public User findUserByUsername(String username);
	public User findUserByMachines(Machine machine);
	public List<User> findAll();
}
