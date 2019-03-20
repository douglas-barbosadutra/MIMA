package com.pCarpet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.LoginDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.LoginService;
import com.pCarpet.utils.JsonResponseBody;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/Login")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService ls) {
		loginService = ls;
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginDTO logindto) {
		UserDTO user;
		//System.out.println(logindto);
		try {
			
			user = loginService.login(logindto.getUsername(), logindto.getPassword());
			//System.out.println("User: "+user);
			//System.out.println(user);
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
