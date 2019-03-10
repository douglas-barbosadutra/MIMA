package com.pCarpet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import com.pCarpet.converter.UserConverter;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;
import com.pCarpet.services.LoginService;
import com.pCarpet.services.UserService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Login")
public class LoginController {
	
	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService ls) {
		loginService = ls;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
		return(loginService.login(username, password));
	}
	
	@RequestMapping(value="/authentication", method= RequestMethod.POST)
	public String authentication(HttpServletRequest request) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		final User user = UserConverter.toEntity(loginService.login(username, password));
		
		if (user != null) {
			UserService.setUserSession(user);
			request.getSession().setAttribute("utente", user);
			
			switch (user.getRankUser()) {
				case 1:
					return "homeAdmin";
				case 0:
					return "homeUser";
			}
		}
		
		return "index";
	}

}
