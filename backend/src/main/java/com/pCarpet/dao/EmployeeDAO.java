package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pCarpet.model.Employee;
import com.pCarpet.model.User;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	public Employee findEmployeeById(int id);
	public List<Employee> findAllByBusinessOwner(User user);
}
