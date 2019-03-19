package com.pCarpet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.LoginDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.LoginService;

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
		UserDTO user = null;
		System.out.println(logindto);
		user = loginService.login(logindto.getUsername(), logindto.getPassword());
		/*try {
			user = loginService.login(logindto.getUsername(), logindto.getPassword());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(user);
		}*/
		/*if (user == null) {
			System.out.println(user);
			return ResponseEntity.badRequest().body(user);
		}*/
		System.out.println(logindto.getUsername() + " " + logindto.getPassword());
		return ResponseEntity.ok().header("", "").body(user);
	}
/*
	public UserDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return (loginService.login(username, password));
	}
*/
}
