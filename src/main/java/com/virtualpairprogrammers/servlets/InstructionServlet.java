package com.virtualpairprogrammers.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtualpairprogrammers.dto.InstructionDTO;
import com.virtualpairprogrammers.dto.TimeDTO;
import com.virtualpairprogrammers.services.InstructionService;
import com.virtualpairprogrammers.services.TempiLavorazioniService;

public class InstructionServlet extends HttpServlet {

	private final InstructionService istruzioneService = new InstructionService();
	private static int idTask;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		if (request != null) {
			final String action = request.getParameter("action").toString();
			
			if(action != null) {
				
				switch(action) {
					case "showInstruction":{
						idTask = Integer.parseInt(request.getParameter("idTask").toString());
						List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
						session.setAttribute("listaIstruzioni", istruzioni);
						getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
						break;
					}	
					case "insertInstruction":{
						String nomeIstruzione = request.getParameter("nomeIstruzione").toString();
						int durata = Integer.parseInt(request.getParameter("durata").toString());
						String codice = request.getParameter("codice").toString();
						InstructionDTO istruzione = new InstructionDTO(nomeIstruzione, durata, idTask, codice);
						istruzioneService.insertIstruzione(istruzione, idTask);
						getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
						break;
					}
					case "insertInstructionOpen":{
						idTask = Integer.parseInt(request.getParameter("idTask").toString());
						getServletContext().getRequestDispatcher("/instructionInsert.jsp").forward(request, response);
						break;
					}
					case "deleteInstruction":{
						String nomeIstruzione = request.getParameter("nomeIstruzione").toString();
						istruzioneService.deleteIstruzione(nomeIstruzione, idTask);
						List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
						session.setAttribute("listaIstruzioni", istruzioni);
						getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
						break;
					}
					case "indietro":
						getServletContext().getRequestDispatcher("/homeUser.jsp").forward(request, response);
						break;
					case "showTime":{
						List<TimeDTO> listaTempi = new ArrayList<>();
						TempiLavorazioniService tempiLavorazioneService = new TempiLavorazioniService();
						listaTempi = tempiLavorazioneService .getAllTempi(idTask);
						session.setAttribute("listaTempi", listaTempi);
						getServletContext().getRequestDispatcher("/timeShow.jsp").forward(request, response);
					}
				}
			}
		}
	}
}
