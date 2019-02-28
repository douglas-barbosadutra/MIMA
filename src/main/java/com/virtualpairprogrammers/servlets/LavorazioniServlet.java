package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.TimeDTO;
import com.virtualpairprogrammers.services.TempiLavorazioniService;

public class LavorazioniServlet extends HttpServlet {

	private final TempiLavorazioniService tempiLavorazioneService = new TempiLavorazioniService();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		if (request != null) {
			final String action = request.getParameter("action").toString();

			if (action != null) {
				switch (action) {
				case "showTime":
					int idTask = Integer.parseInt(request.getParameter("idTask").toString());
					List<TimeDTO> listaTempi = new ArrayList<>();
					listaTempi = tempiLavorazioneService.getAllTempi(idTask);
					session.setAttribute("listaTempi", listaTempi);
					getServletContext().getRequestDispatcher("/timeShow.jsp").forward(request, response);
					break;
				case "indietro":
					getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
					break;
				}

			}
		}
	}
}
