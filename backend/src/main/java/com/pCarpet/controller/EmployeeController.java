package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.EmployeeService;
import com.pCarpet.services.UserService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	private EmployeeService employeeService;
	private UserService userService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService, UserService userService) {
		this.employeeService = employeeService;
		this.userService = userService;
	}
	
	@RequestMapping(value="/insertEmployee", method = RequestMethod.POST)
	public EmployeeDTO insertEmployee(@RequestBody EmployeeDTO employeeDTO) {
		System.out.println(employeeDTO);
		UserDTO userDTO = employeeDTO.getUser();
		userDTO.setRank(2);
		userService.insertUser(userDTO);
		
		userDTO = userService.getUserByUsername(userDTO.getUsername());
		employeeDTO.setUser(userDTO);
		System.out.println(employeeDTO);
		return employeeService.insertEmployee(employeeDTO);
	}
	
	@RequestMapping(value="/assignTask", method = RequestMethod.PUT)
	public EmployeeDTO assignTask(@RequestBody EmployeeDTO employee) {
		return employeeService.insertEmployee(employee);
	}
	
	@RequestMapping(value="/showEmployee", method = RequestMethod.GET)
	public List<EmployeeDTO> getEmployeeByBusinessOwner(@RequestParam(value="idBusinessOwner") int idBusinessOwner){
		return this.employeeService.getEmployeeByIdBusinessOwner(idBusinessOwner);
	}
	
	@RequestMapping(value="/findEmployee", method = RequestMethod.GET)
	public EmployeeDTO findEmployee(@RequestParam(value="idUser") int idUser) {
		return this.employeeService.getEmployeeByUser(idUser);
	}
	
	@RequestMapping(value="/deleteEmployee", method = RequestMethod.DELETE)
	public boolean deleteEmployee(@RequestParam(value="idUser") int idUser) {
		return this.userService.deleteUser(idUser);
	}
}
