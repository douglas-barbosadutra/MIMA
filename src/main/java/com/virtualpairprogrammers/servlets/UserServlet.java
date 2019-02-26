package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.UserDTO;
import com.virtualpairprogrammers.services.UserService;

public class UserServlet extends HttpServlet {
	
	private final UserService userService = new UserService();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession();

		if (request != null) {
			final String action = request.getParameter("action").toString();
			
			if(action != null) {
			
			switch(action) {
					
				case "openInsertUser":
					
					getServletContext().getRequestDispatcher("/userInsert.jsp").forward(request, response);
					
				break;
					
				case "insertUser":{
					
					String username = request.getParameter("username").toString();
					String password = request.getParameter("password").toString();
					String nome = request.getParameter("nome").toString();
					String cognome = request.getParameter("cognome").toString();
					String email = request.getParameter("email").toString();
					String telefono = request.getParameter("telefono").toString();
					int rank = Integer.parseInt(request.getParameter("rank").toString());
					userService.insertUser(username, password, nome, cognome, email, telefono, rank);
					
					getServletContext().getRequestDispatcher("/homeAdmin.jsp").forward(request, response);
					
				} break;
					
				case "showUser":
					
					List<UserDTO> users = userService.getAllUsers();
					session.setAttribute("users_list", users);
					
					getServletContext().getRequestDispatcher("/userShow.jsp").forward(request, response);
					
					break;
				
				case "deleteUser":
					
					int id = Integer.parseInt(request.getParameter("id").toString());
					userService.deleteUser(id);
					
					getServletContext().getRequestDispatcher("/homeAdmin.jsp").forward(request, response);
					
				break;
				
				case "openUpdateUser":{
					
					getServletContext().getRequestDispatcher("/userUpdate.jsp").forward(request, response);
					
				} break;
				
				case "updateUser": {
					
					String nome = request.getParameter("nome").toString();
					String cognome = request.getParameter("cognome").toString();
					String username = request.getParameter("username").toString();
					String password = request.getParameter("password").toString();
					String email = request.getParameter("email").toString();
					String telefono = request.getParameter("telefono").toString();
					
					userService.updateUser(nome, cognome, email, telefono, username, password);
					getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
				
					
				}break;
					
				case "indietro":
					
					getServletContext().getRequestDispatcher("/homeAdmin.jsp").forward(request, response);
					
				break;
				
				case "indietroUser":{
					
					getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
	
				}break;
				
				case "logout":
					
					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
					
				break;
					
				}
			
			}
			
		}
	}

}