package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private static int idMacchinario = 0;
	private static int idSchedulazione = 0;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();

		if (request != null) {

			final String action = request.getParameter("action").toString();

			if (action != null) {

				switch (action) {

				case "chooseMachine": {

					idMacchinario = Integer.parseInt(request.getParameter("id").toString());
					getServletContext().getRequestDispatcher("/TaskServlet?action=chooseMachine&id=" + idMacchinario)
							.forward(request, response);

				}
					break;

				case "showScheduling": {

					if (idMacchinario == 0)
						getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement")
								.forward(request, response);

					else {

						session.setAttribute("showScheduling", "list");

						callShowView(session, request, response);
					}
					break;
				}
				case "insertSchedulingOpen": {

					if (idMacchinario == 0)
						getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement")
								.forward(request, response);

					else
						getServletContext().getRequestDispatcher("/schedulingInsert.jsp").forward(request, response);

				}
					break;

				case "insertScheduling": {
					String nome = request.getParameter("nome");
					String dataInizio = request.getParameter("dataInizio");
					String dataFine = request.getParameter("dataFine");
					schedulingService.insertScheduling(new SchedulingDTO(nome, getTimestampByString(dataInizio),
							getTimestampByString(dataFine), idMacchinario));
					callShowView(session, request, response);
					break;
				}

				case "managementScheduling": {
					if (idMacchinario == 0)
						getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement")
								.forward(request, response);
					else {
						session.setAttribute("showScheduling", "management");
						callShowView(session, request, response);
					}
					break;
				}

				case "deleteSchedulingManagement": {
					if (idMacchinario == 0)
						getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement")
								.forward(request, response);
					else {
						session.setAttribute("showScheduling", "delete");
						callShowView(session, request, response);
					}
					break;
				}

				case "deleteScheduling": {
					int id = Integer.parseInt(request.getParameter("id").toString());
					schedulingService.deleteScheduling(id);
					callShowView(session, request, response);
					break;
				}

				case "modifyScheduling": {
					String dataInizio = request.getParameter("dataInizio");
					String dataFine = request.getParameter("dataFine");
					schedulingService.updateScheduling(idSchedulazione, getTimestampByString(dataInizio),
							getTimestampByString(dataFine));
					callShowView(session, request, response);
					break;
				}
				case "updateSchedulingManagement": {
					idSchedulazione = Integer.parseInt(request.getParameter("id").toString());
					getServletContext().getRequestDispatcher("/schedulingModify.jsp").forward(request, response);
				}
				case "modifySchedulingOpen": {
					if (idMacchinario == 0)
						getServletContext().getRequestDispatcher("/MachineServlet?action=chooseMachineManagement")
								.forward(request, response);
					else {
						session.setAttribute("showScheduling", "update");
						callShowView(session, request, response);
						break;
					}
				}
				case "indietro": {
					getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
					break;
				}
				}
			}
		}
	}

	private Timestamp getTimestampByString(String stringData) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date data = new Date();
		try {
			data = dateFormat.parse(stringData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (new Timestamp(data.getTime()));
	}

	private void callShowView(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SchedulingDTO> listaScheduling = schedulingService.getAllScheduling(idMacchinario);
		session.setAttribute("listaScheduling", listaScheduling);
		getServletContext().getRequestDispatcher("/schedulingList.jsp").forward(request, response);
	}
}
