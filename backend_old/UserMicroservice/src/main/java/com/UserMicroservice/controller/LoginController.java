package com.UserMicroservice.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserMicroservice.dto.LoginDTO;
import com.UserMicroservice.dto.UserDTO;
import com.UserMicroservice.dto.UserLoggedDTO;
import com.UserMicroservice.service.LoginService;
import com.UserMicroservice.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	public LoginController() {
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoggedDTO> login(@RequestBody LoginDTO loginDTO) {
		
		UserDTO user = loginService.login(loginDTO);
		
		if(user != null) {
			
			try {
				String jwt = loginService.createJwt(""+user.getId(), user.getName()+" "+user.getSurname(), ""+user.getRank(), new Date());
				UserLoggedDTO userLogged = new UserLoggedDTO(jwt,user.getRank());
				return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(userLogged);
				
			} catch (UnsupportedEncodingException e) {
				
				 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
		}
		
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		
	}

}
