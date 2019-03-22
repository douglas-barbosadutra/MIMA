package com.mima.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mima.dto.LoginDTO;
import com.mima.dto.UserDTO;
import com.mima.services.LoginService;
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/Login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	public LoginController() {	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginDTO logindto) {
		String username = logindto.getUsername();
		String password = logindto.getPassword();
		UserDTO user;
		try {
			//System.out.println(username + " " + password);
			user = loginService.login(username, password);
			//System.out.println("User: "+user);
			if (user == null) {
				//System.out.println(user);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(user);
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		
	}
/*
	public UserDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return (loginService.login(username, password));
	}
*/
}
