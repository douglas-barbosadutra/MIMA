package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.MachineDTO;
import com.virtualpairprogrammers.dto.TaskDTO;
import com.virtualpairprogrammers.services.TaskService;

public class TaskServlet extends HttpServlet{

	private final TaskService taskService = new TaskService();
	private static int idMacchinario = 0;
	
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession();

		if (request != null) {
			final String action = request.getParameter("action").toString();
			
			if(action != null) {
				
			
				switch(action) {
				
					case "chooseMachine":{
						
						idMacchinario = Integer.parseInt(request.getParameter("id").toString());
						session.setAttribute("idMacchinarioScelto", idMacchinario);
						
						session.setAttribute("showTask", "list");
						callShowView(session, request, response);
						
					} break;
					
					case "chooseTask":{
						
						if(idMacchinario == 0)
							getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement").forward(request, response);
						
						else {
							
							session.setAttribute("showTask", "choose");
							callShowView(session, request, response);
							
						}
						
					}break;
				
					case "deleteTaskManagement":{
						
						if(idMacchinario == 0)
							getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement").forward(request, response);
						
						else {
							
							session.setAttribute("showTask", "delete");
							callShowView(session, request, response);
						}						
						
					}break;
					
					case "deleteTask":{
						
						int id = Integer.parseInt(request.getParameter("id").toString());
						taskService.deleteTask(id);
						
						session.setAttribute("showTask", "delete");
						callShowView(session, request, response);
								
					}break;
					
					case "insertTask":{
						String descrizione = request.getParameter("descrizione").toString();
						taskService.insertTask(descrizione, idMacchinario);
						
						session.setAttribute("showTask", "list");
						callShowView(session, request, response);
						
					} break;
					
					case "showTask":{
						
						if(idMacchinario == 0)
							getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement").forward(request, response);
						
						else {
							
							session.setAttribute("showTask", "list");
							callShowView(session, request, response);
						}
						
					}break;
					
					case "openInsertTask":{
						
						if(idMacchinario == 0)
							getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement").forward(request, response);
						
						else 
							getServletContext().getRequestDispatcher("/taskInsert.jsp").forward(request, response);
						
					} break;
					
				}
			}
		}
	}
	
	private void callShowView(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<TaskDTO> tasks = taskService.getAllTasks(idMacchinario);
		session.setAttribute("taskList", tasks);
		getServletContext().getRequestDispatcher("/taskShow.jsp").forward(request, response);
	}
}
