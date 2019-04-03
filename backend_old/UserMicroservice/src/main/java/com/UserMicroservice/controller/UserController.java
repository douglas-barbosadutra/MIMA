package com.UserMicroservice.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UserMicroservice.dto.ParamDTO;
import com.UserMicroservice.dto.UserDTO;
import com.UserMicroservice.service.UserService;
import com.UserMicroservice.utils.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController() {	}
	
	@PostMapping("/insertUser")
	public ResponseEntity<UserDTO> insertUser(@RequestBody ParamDTO param) {

		LinkedHashMap user = (LinkedHashMap) param.getParam();
		
		try {
			int rank = this.getRankFromJwt(param.getJwt());
			
			if(rank == 1)
				return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(new UserDTO(Integer.parseInt(user.get("id").toString()), user.get("username").toString(), user.get("password").toString(), user.get("name").toString(), user.get("surname").toString(), user.get("email").toString(), user.get("phone").toString(), Integer.parseInt(user.get("rank").toString()))));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@PostMapping("/deleteUser")
	public ResponseEntity<Boolean> deleteUser(@RequestBody ParamDTO param) {
		System.out.println(param);
		try {
			
			int rank = this.getRankFromJwt(param.getJwt());
			
			if(rank == 1)
				return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser((int)param.getParam()));
			else
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@GetMapping("/showUser")
	public ResponseEntity<List<UserDTO>> showUser(@RequestParam(value="jwt") String jwt) {	
		
		try {
			int rank = this.getRankFromJwt(jwt);
			System.out.println("rank: "+rank);
			
			if(rank == 1)
				return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
		
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<UserDTO> updateUser(@RequestBody ParamDTO param) {		
		
		try {
			Map<String, Object> data = JwtUtils.jwt2Map(param.getJwt());
			int idUser = Integer.parseInt(data.get("subject").toString());
			int rank = Integer.parseInt(data.get("scope").toString());
			LinkedHashMap user = (LinkedHashMap) param.getParam();
			
		if(rank != 1)
				return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(new UserDTO(idUser, user.get("username").toString(), user.get("password").toString(), user.get("name").toString(), user.get("surname").toString(), user.get("email").toString(), user.get("phone").toString(), Integer.parseInt(user.get("rank").toString()))));
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@GetMapping("/findUser")
	public ResponseEntity<UserDTO> findUser(@RequestParam(value="jwt") String jwt) {	
		
		try {
			
			Map<String, Object> data = JwtUtils.jwt2Map(jwt);
			int idUser = Integer.parseInt(data.get("subject").toString());
			int rank = Integer.parseInt(data.get("scope").toString());
			System.out.println("id: "+idUser);
			
			if(rank != 1)
				return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(idUser));
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	private int getRankFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int rank = Integer.parseInt(data.get("scope").toString());
		
		return rank;
	}

}
