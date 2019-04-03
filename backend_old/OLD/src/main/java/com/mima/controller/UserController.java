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

import com.mima.dto.UserDTO;
import com.mima.services.UserService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController() {	}
	
	@PostMapping("/insertUser")
	public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO user) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(user));
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<Boolean> deleteUser(@RequestParam(value="idUser") int idUser) {		
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(idUser));
	}
	
	@GetMapping("/showUser")
	public ResponseEntity<List<UserDTO>> showUser() {		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<UserDTO> showUser(@RequestBody UserDTO user) {		
		return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(user));
	}
	
	@GetMapping("/findUser")
	public ResponseEntity<UserDTO> findUser(@RequestParam(value="idUser") int idUser) {		
		return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(idUser));
	}

}
