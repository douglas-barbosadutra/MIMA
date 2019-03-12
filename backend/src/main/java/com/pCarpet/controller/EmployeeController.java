package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.EmployeeService;
import com.pCarpet.services.UserService;

@CrossOrigin(value="*")
@Controller
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
	public EmployeeDTO insertEmployee(@RequestBody UserDTO userdto, @RequestBody Integer idBusinessOwner) {
		userdto.setRank(2);
		userService.insertUser(userdto);
		userdto = userService.getUserByUsername(userdto.getUsername());
		EmployeeDTO employee = new EmployeeDTO();
		employee.setIdUser(userdto.getId());
		employee.setIdBusinessOwner(idBusinessOwner);
		employee.setName(userdto.getName());
		return employeeService.insertEmployee(employee);
	}
	
	@RequestMapping(value="/assignTask", method = RequestMethod.POST)
	public EmployeeDTO assignTask(@RequestBody EmployeeDTO employee, @RequestBody Integer idTask) {
		employee.setIdTask(idTask);
		return employeeService.insertEmployee(employee);
	}
	
	@RequestMapping(value="/showEmployee", method = RequestMethod.POST)
	public List<EmployeeDTO> getEmployeeByBusinessOwner(@RequestBody UserDTO BusinessOwner){
		return this.employeeService.getEmployeeByIdBusinessOwner(BusinessOwner.getId());
	}
	
	@RequestMapping(value="/findEmployee", method = RequestMethod.GET)
	public EmployeeDTO findEmployee(@RequestParam(value="idEmployee") int idEmployee) {
		return this.employeeService.getEmployeeById(idEmployee);
	}
}
