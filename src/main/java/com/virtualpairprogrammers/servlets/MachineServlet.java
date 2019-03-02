package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.InstructionDTO;
import com.virtualpairprogrammers.dto.MachineDTO;
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
				
				case "openInsertMachine":{
					
					getServletContext().getRequestDispatcher("/machineInsert.jsp").forward(request, response);
					
					
				}break;
				
				case "insertMachine":{
					
					String nome = request.getParameter("nome").toString();
					String modello = request.getParameter("modello").toString();
					
					machineService.insertMachine(nome, modello);
					
					session.setAttribute("showMachine", "list");
					callShowView(session, request, response);
					
				} break;
				
				case "showMachine":{
					
					session.setAttribute("showMachine", "list");
					callShowView(session, request, response);
					
				} break;
				
				case "chooseMachineManagement":{
					
					session.setAttribute("showMachine", "choose");
					callShowView(session, request, response);
					
				} break;
				
				case "deleteMachineManagement":{
					
					session.setAttribute("showMachine", "delete");
					callShowView(session, request, response);
					
				}break;
				
				case "deleteMachine":{
					
					int id = Integer.parseInt(request.getParameter("id").toString());
					machineService.deleteMachine(id);
					
					session.setAttribute("showMachine", "delete");
					callShowView(session, request, response);
					
				} break;
					
					
				}
			
			}
			
		}
	}
	
	private void callShowView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<MachineDTO> machines = machineService.getAllMachines();
		session.setAttribute("machines_list", machines);
		getServletContext().getRequestDispatcher("/machineShow.jsp").forward(request, response);
	}

}
