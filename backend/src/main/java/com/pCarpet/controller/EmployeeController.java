package com.pCarpet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.EmployeeService;
import com.pCarpet.services.UserService;

@CrossOrigin(value="*")
@Controller
@RequestMapping("/Login")
public class EmployeeController {

	private EmployeeService employeeService;
	private UserService userService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService, UserService userService) {
		this.employeeService = employeeService;
		this.userService = userService;
	}
	
	@RequestMapping(value="/insertEmployee")
	public String insertEmployee(HttpServletRequest request) {
		UserDTO userdto = (UserDTO)request.getAttribute("user");
		int idBusinessOwner = (int)(request.getAttribute("idBusinessOwner"));
		userdto.setRank(2);
		userService.insertUser(userdto);
		userdto = userService.getUserByUsername(userdto.getUsername());
		EmployeeDTO employee = new EmployeeDTO();
		employee.setIdUser(userdto.getId());
		employee.setIdBusinessOwner(idBusinessOwner);
		employee.setName(userdto.getName());
		employeeService.insertEmployee(employee);
		return "";
	}
	
	@RequestMapping(value="/insertEmployee")
	public String assignTask(HttpServletRequest request) {
		EmployeeDTO employee = (EmployeeDTO)request.getAttribute("employee");
		int idTask = (int)request.getAttribute("idTask");
		employee.setIdTask(idTask);
		employeeService.insertEmployee(employee);
		return "";
	}
}
