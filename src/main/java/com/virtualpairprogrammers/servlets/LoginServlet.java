package com.virtualpairprogrammers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.domain.User;
import com.virtualpairprogrammers.services.LoginService;
import com.virtualpairprogrammers.services.UserService;

public class LoginServlet extends HttpServlet {
	
	private final LoginService loginService = new LoginService();;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession();

		if (request != null) {
			final String username = request.getParameter("username").toString();
			final String password = request.getParameter("password").toString();

			final User user = loginService.login(username, password);
			
			if (user != null) {
				UserService.setUserSession(user);
				session.setAttribute("utente", user.getName());

				switch (user.getRank()) {
					case 1:
						getServletContext().getRequestDispatcher("/homeAdmin.jsp").forward(request, response);
						break;
					case 0:
						getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
						break;
				}
			}else {
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}

}
