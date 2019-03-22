package com.mima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mima.dto.EmployeeDTO;
import com.mima.dto.UserDTO;
import com.mima.services.EmployeeService;
import com.mima.services.UserService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	
	public EmployeeController() {}
	
	@PostMapping("/insertEmployee")
	public ResponseEntity<EmployeeDTO> insertEmployee(@RequestBody EmployeeDTO employeeDTO) {
		System.out.println(employeeDTO);
		UserDTO userDTO = employeeDTO.getUser();
		userDTO.setRank(2);
		userService.insertUser(userDTO);
		
		userDTO = userService.getUserByUsername(userDTO.getUsername());
		employeeDTO.setUser(userDTO);
		System.out.println(employeeDTO);
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.insertEmployee(employeeDTO));
	}
	
	@PutMapping("/assignTask")
	public ResponseEntity<EmployeeDTO> assignTask(@RequestBody EmployeeDTO employee) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.insertEmployee(employee));
	}
	
	@GetMapping("/showEmployee")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeByBusinessOwner(@RequestParam(value="idBusinessOwner") int idBusinessOwner){
		return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getEmployeeByIdBusinessOwner(idBusinessOwner));
	}
	
	@GetMapping("/findEmployee")
	public ResponseEntity<EmployeeDTO> findEmployee(@RequestParam(value="idUser") int idUser) {
		return ResponseEntity.status(HttpStatus.OK).body(this.employeeService.getEmployeeByUser(idUser));
	}
	
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<Boolean> deleteEmployee(@RequestParam(value="idUser") int idUser) {

		return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(idUser));
	}
}
