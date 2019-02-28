package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.SchedulingDTO;
import com.virtualpairprogrammers.services.SchedulingService;

public class SchedulingServlet extends HttpServlet {

	private final SchedulingService schedulingService = new SchedulingService();
	private static int idMacchinario;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		if (request != null) {
			final String action = request.getParameter("action").toString();
			if (action != null) {
				switch (action) {
					case "showScheduling":{
						idMacchinario = Integer.parseInt(request.getParameter("idMacchinario").toString());
						List<SchedulingDTO> listaScheduling = schedulingService.getAllScheduling(idMacchinario);
						session.setAttribute("listaScheduling", listaScheduling);
						getServletContext().getRequestDispatcher("/schedulingList.jsp").forward(request, response);
						break;
					}
					case "insertSchedulingOpen":{
						getServletContext().getRequestDispatcher("/schedulingInsert.jsp").forward(request, response);
						break;
					}
					case "insertScheduling":{
						String nome = request.getParameter("nome");
						String dataInizio = request.getParameter("dataInizio");
						String dataFine = request.getParameter("dataFine");
						schedulingService.insertScheduling(new SchedulingDTO(nome, dataInizio, dataFine, idMacchinario));
						break;
					}
					case "deleteScheduling":{
						int id = Integer.parseInt(request.getParameter("id").toString());
						schedulingService.deleteScheduling(id);
						List<SchedulingDTO> listaScheduling = schedulingService.getAllScheduling(idMacchinario);
						session.setAttribute("listaScheduling", listaScheduling);
						getServletContext().getRequestDispatcher("/schedulingList.jsp").forward(request, response);
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
