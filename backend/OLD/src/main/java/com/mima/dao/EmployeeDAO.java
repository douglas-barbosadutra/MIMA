package com.mima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mima.model.Employee;
import com.mima.model.User;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{
	public Employee findEmployeeByUser(User user);
	public List<Employee> findAllByBusinessOwner(User user);
}
