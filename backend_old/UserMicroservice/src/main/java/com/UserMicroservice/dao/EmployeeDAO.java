package com.UserMicroservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserMicroservice.model.Employee;
import com.UserMicroservice.model.User;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	
	public Employee findEmployeeByUser(User user);
	public List<Employee> findAllByBusinessOwner(User user);
}
