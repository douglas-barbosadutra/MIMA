package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.TaskDTO;
import com.virtualpairprogrammers.dto.TaskSchedulatiDTO;
import com.virtualpairprogrammers.services.OperazioneSchedulazioneService;
import com.virtualpairprogrammers.services.TaskService;

public class OperazioneSchedulazioneServlet extends HttpServlet {

	private OperazioneSchedulazioneService operazioneSchedulazioneService = new OperazioneSchedulazioneService();
	private TaskService taskService = new TaskService();

	private static int idSchedulazione;
	private static int idMacchinario;
	private static int prossimo;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final HttpSession session = request.getSession();

		if (request != null) {

			final String action = request.getParameter("action").toString();

			if (action != null) {

				switch (action) {

				case "insertOperazione": {
					int idTask = Integer.parseInt(request.getParameter("id").toString());
					operazioneSchedulazioneService.insertOperazioneSchedulazione(idSchedulazione, idTask, prossimo);
					this.callShowView(session, request, response);
					break;
				}

				case "deleteOperazioneScheduling": {

					int id = Integer.parseInt(request.getParameter("id").toString());
					operazioneSchedulazioneService.deleteOperazioneSchedulazione(id, idSchedulazione, idMacchinario);
					this.callShowView(session, request, response);
					break;
				}

				case "showOperazioni": {
					idSchedulazione = Integer.parseInt(request.getParameter("idSchedulazione").toString());
					idMacchinario = Integer.parseInt(request.getParameter("idMacchinario").toString());
					session.setAttribute("idSchedulazioneScelta", idSchedulazione);
					this.callShowView(session, request, response);
					break;
				}

				case "modifyOrderScheduling": {

					int oldPosition = Integer.parseInt(request.getParameter("oldPosition").toString());
					int newPosition = Integer.parseInt(request.getParameter("newPosition").toString());
					operazioneSchedulazioneService.modifyPosition(idSchedulazione, idMacchinario, oldPosition,
							newPosition);
					this.callShowView(session, request, response);
					break;

				}
				case "indietro": {

					getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
					break;
				}
				}
			}
		}
	}

	private void callShowView(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<TaskDTO> listaTaskDisponibili = taskService.getAllTasks(idMacchinario);
		List<TaskSchedulatiDTO> listaTaskScheduling = operazioneSchedulazioneService.getTaskSchedulati(idSchedulazione,
				idMacchinario);
		prossimo = listaTaskScheduling.size() + 1;
		session.setAttribute("listaTaskDisponibili", listaTaskDisponibili);
		session.setAttribute("listaTaskScheduling", listaTaskScheduling);
		getServletContext().getRequestDispatcher("/operazioneSchedulazione.jsp").forward(request, response);
	}

}
