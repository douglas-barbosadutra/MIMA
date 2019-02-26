package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.TaskDTO;
import com.virtualpairprogrammers.services.TaskService;

public class TaskServlet extends HttpServlet{

	private final TaskService taskService = new TaskService();
	private static int idMacchinario;
	
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final HttpSession session = request.getSession();

		if (request != null) {
			final String action = request.getParameter("action").toString();
			
			if(action != null) {
			
				switch(action) {
					case "deleteTask":{
						int id = Integer.parseInt(request.getParameter("id").toString());
						taskService.deleteTask(id);
						getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
						break;
					}
					case "insertTask":{
						String descrizione = request.getParameter("descrizione").toString();
						taskService.insertTask(descrizione, idMacchinario);
						getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
						break;
					}
					case "showTask":{
						int idMacchinario = Integer.parseInt(request.getParameter("id_macchinario").toString());
						List<TaskDTO> tasks = taskService.getAllTasks(idMacchinario);
						session.setAttribute("taskList", tasks);
						getServletContext().getRequestDispatcher("/taskShow.jsp").forward(request, response);
						break;
					}
					case "openInsertTask":{
						idMacchinario = Integer.parseInt(request.getParameter("id_macchinario").toString());
						getServletContext().getRequestDispatcher("/taskInsert.jsp").forward(request, response);
						break;
					}
					case "indietro":{
						getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
						break;
					}
				}
			}
		}
	}
}
