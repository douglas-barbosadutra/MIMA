package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.Employee;
import com.pCarpet.model.User;

public interface EmployeeDAO extends CrudRepository<Employee, Integer>{
	public Employee findEmployeeByUser(User user);
	public List<Employee> findAllByBusinessOwner(User user);
}
