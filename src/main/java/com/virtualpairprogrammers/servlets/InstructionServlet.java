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
	private static int idTask = 0;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		if (request != null) {
			final String action = request.getParameter("action").toString();
			
			if(action != null) {
				
				switch(action) {
				
					case "chooseTask":{
						
						idTask = Integer.parseInt(request.getParameter("id").toString());
						session.setAttribute("idTaskScelto", idTask);
						
						session.setAttribute("showInstruction", "list");
						
						List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
						session.setAttribute("listaIstruzioni", istruzioni);
						getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
						
					} break;
					
					case "showInstruction":{
						
						if(idTask == 0) {	
							getServletContext().getRequestDispatcher("/TaskServlet?action=chooseTask").forward(request, response);
						}
							
						else {
							
							session.setAttribute("showInstruction", "list");
							
							List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
							session.setAttribute("listaIstruzioni", istruzioni);
							getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
						}
						
					} break;
					
					case "insertInstruction":{
						
							String nomeIstruzione = request.getParameter("nome").toString();
							int durata = Integer.parseInt(request.getParameter("durata").toString());
							String codice = request.getParameter("codice").toString();
							
							InstructionDTO istruzione = new InstructionDTO(nomeIstruzione, durata, idTask, codice);
							istruzioneService.insertIstruzione(istruzione, idTask);
							
							session.setAttribute("showInstruction", "list");
							
							List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
							session.setAttribute("listaIstruzioni", istruzioni);
							getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
												
					}break;
					
					case "insertInstructionOpen":{
						
						if(idTask == 0)
							getServletContext().getRequestDispatcher("/TaskServlet?action=chooseTask").forward(request, response);
						
						else 
							getServletContext().getRequestDispatcher("/instructionInsert.jsp").forward(request, response);
							
					}break;
					
					case "deleteInstructionManagement":{
						
						if(idTask == 0)
							getServletContext().getRequestDispatcher("/TaskServlet?action=chooseTask").forward(request, response);
						
						else {
							
							session.setAttribute("showInstruction", "delete");
							
							List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
							session.setAttribute("listaIstruzioni", istruzioni);
							getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
						}
						
					} break;
					
					case "deleteInstruction":{
						
						String nomeIstruzione = request.getParameter("nomeIstruzione").toString();
						istruzioneService.deleteIstruzione(nomeIstruzione, idTask);
						
						session.setAttribute("showInstruction", "list");
						List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
						session.setAttribute("listaIstruzioni", istruzioni);
						getServletContext().getRequestDispatcher("/instructionShow.jsp").forward(request, response);
						break;
					}
						
					case "showTime":{
						
						if(idTask == 0)
							getServletContext().getRequestDispatcher("/TaskServlet?action=chooseTask").forward(request, response);
						
						else {
							
							List<TimeDTO> listaTempi = new ArrayList<>();
							TempiLavorazioniService tempiLavorazioneService = new TempiLavorazioniService();
							listaTempi = tempiLavorazioneService .getAllTempi(idTask);
							session.setAttribute("listaTempi", listaTempi);
							getServletContext().getRequestDispatcher("/timeShow.jsp").forward(request, response);
						}
				
					} break;
				}
			}
		}
	}
}
