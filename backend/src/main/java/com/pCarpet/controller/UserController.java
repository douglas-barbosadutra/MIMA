package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String insertUser(HttpServletRequest request) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		int rank = Integer.parseInt(request.getParameter("rank"));
		
		UserDTO userdto = new UserDTO(0, username, password, nome, cognome, email, telefono, rank);
		
		userService.insertUser(userdto);
		
		return "homeAdmin";
	}
	
	@RequestMapping(value="/updateUser", method= RequestMethod.POST)
	public String updateUser(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		
		UserDTO userdto = new UserDTO(id, username, password, nome, cognome, email, telefono, 0);
		
		userService.insertUser(userdto);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/deleteUser" , method= RequestMethod.GET)
	public String deleteUser(HttpServletRequest request) {		
		
		int id = Integer.parseInt(request.getParameter("id"));
		userService.deleteUser(id);
		
		return "homeAdmin";
	}
	
	@RequestMapping(value="/showUser" , method= RequestMethod.GET)
	public String showUser(HttpServletRequest request) {
		
		String showUser = request.getParameter("showUser");
		
		List<UserDTO> users = userService.getAllUsers();
		
		request.getSession().setAttribute("users_list", users);
		request.getSession().setAttribute("showUser", showUser);
		
		return "userShow";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {		
		return "index";
	}
}
