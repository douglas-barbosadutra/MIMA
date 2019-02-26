package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.UserDTO;
import com.virtualpairprogrammers.services.MachineService;

public class MachineServlet extends HttpServlet{
	
	private final MachineService machineService = new MachineService();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession();

		if (request != null) {
			final String action = request.getParameter("action").toString();
			
			if(action != null) {
			
			switch(action) {
					
				case "indietro":
					
					getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
					
				break;
					
				}
			
			}
			
		}
	}

}
