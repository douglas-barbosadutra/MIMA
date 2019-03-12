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

import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.UserService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/User")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService us) {
		userService = us;
	}
	
	@RequestMapping(value="/insertUser", method= RequestMethod.POST)
	public UserDTO insertUser(@RequestBody UserDTO user) {
		return userService.insertUser(user);
	}
	
	@RequestMapping(value="/deleteUser" , method= RequestMethod.DELETE)
	public boolean deleteUser(@RequestParam(value="idUser") int idUser) {		
		return userService.deleteUser(idUser);
	}
	
	@RequestMapping(value="/showUser" , method= RequestMethod.GET)
	public List<UserDTO> showUser() {		
		return userService.getAllUsers();
	}
	
	@RequestMapping(value="/updateUser" , method= RequestMethod.PUT)
	public UserDTO showUser(@RequestBody UserDTO user) {		
		return userService.insertUser(user);
	}
	
	@RequestMapping(value="/findUser" , method= RequestMethod.GET)
	public UserDTO findUser(@RequestParam(value="idUser") int idUser) {		
		return userService.findUserById(idUser);
	}

}
