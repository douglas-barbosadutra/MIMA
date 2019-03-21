package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pCarpet.model.Machine;
import com.pCarpet.model.User;


public interface UserDAO extends JpaRepository<User, Integer>{
	public User findUserByUsernameAndPassword(String username, String password);
	public User findUserById(int id);
	public User findUserByUsername(String username);
	public User findUserByMachines(Machine machine);
	public List<User> findAll();
	
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    int getLastInsertId();
}
