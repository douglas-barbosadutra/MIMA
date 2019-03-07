package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.services.OperationSchedulingService;
import com.pCarpet.services.TaskService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/OperationScheduling")
public class OperationSchedulingController {

	private static int prossimo = 0;
	OperationSchedulingService operationSchedulingService;
	private TaskService taskService;

	@Autowired
	public OperationSchedulingController(TaskService taskService,
			OperationSchedulingService operationSchedulingService) {
		this.operationSchedulingService = operationSchedulingService;
		this.taskService = taskService;
	}
	
	@RequestMapping(value = "/insertOperazionScheduling", method = RequestMethod.GET)
	public String insertOperazionScheduling(HttpServletRequest request) {
		int idTask = Integer.parseInt(request.getParameter("id").toString());
		operationSchedulingService.insertOperazioneSchedulazione(UserService.idScheduling, idTask, prossimo);
		return callShowView(request);
	}
	
	@RequestMapping(value = "/deleteOperazionScheduling", method = RequestMethod.GET)
	public String deleteOperazionScheduling(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id").toString());
		operationSchedulingService.deleteOperationScheduling(id, UserService.idScheduling, UserService.idMacchinario);
		return callShowView(request);
	}
	
	@RequestMapping(value = "/modifyOperazionScheduling", method = RequestMethod.GET)
	public String modifyOperazionScheduling(HttpServletRequest request) {
		int oldPosition = Integer.parseInt(request.getParameter("oldPosition").toString());
		int newPosition = Integer.parseInt(request.getParameter("newPosition").toString());
		operationSchedulingService.modifyPosition( UserService.idScheduling, UserService.idMacchinario, oldPosition, newPosition);
		return callShowView(request);
	}

	@RequestMapping(value = "/showOperazionScheduling", method = RequestMethod.GET)
	public String showOperazionScheduling(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("idScheduling").toString());
		UserService.idScheduling = id;
		return callShowView(request);
	}
	
	private String callShowView(HttpServletRequest request) {
		List<TaskDTO> listaTaskDisponibili = taskService.getAllTasks(UserService.idMacchinario);
		List<TaskScheduledDTO> listaTaskScheduling = operationSchedulingService.getTaskSchedulati(UserService.idScheduling, UserService.idMacchinario);
		prossimo = listaTaskScheduling.size() + 1;
		request.getSession().setAttribute("listaTaskDisponibili", listaTaskDisponibili);
		request.getSession().setAttribute("listaTaskScheduling", listaTaskScheduling);
		return "operazioneSchedulazione";
	}
}
