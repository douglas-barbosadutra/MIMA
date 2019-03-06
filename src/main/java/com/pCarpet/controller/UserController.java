package com.pCarpet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;
import com.pCarpet.services.LoginService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/User")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService us) {
		userService = us;
	}
	
	@RequestMapping(value="/openInsertUser")
	public String openInsertUser(HttpServletRequest request) {
		return "userInsert";
	}
	
	@RequestMapping(value="/insertUser", method= RequestMethod.POST)
	public String insertUser(HttpServletRequest request) {
		
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		String nome = request.getParameter("nome").toString();
		String cognome = request.getParameter("cognome").toString();
		String email = request.getParameter("email").toString();
		String telefono = request.getParameter("telefono").toString();
		int rank = Integer.parseInt(request.getParameter("rank").toString());
		
		UserDTO userdto = new UserDTO(0, username, password, nome, cognome, email, telefono, rank);
		
		userService.insertUser(userdto);
		
		request.getSession().setAttribute("showUser", "list");
		
		return "index";
	}
	
}
