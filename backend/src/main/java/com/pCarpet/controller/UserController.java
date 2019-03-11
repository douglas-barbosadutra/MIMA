package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.UserService;

@Controller
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
	
	@RequestMapping(value="/deleteUser" , method= RequestMethod.GET)
	public boolean deleteUser(@RequestBody UserDTO user) {		
		return userService.deleteUser(user.getId());
	}
	
	@RequestMapping(value="/showUser" , method= RequestMethod.GET)
	public List<UserDTO> showUser() {		
		return userService.getAllUsers();
	}

}
