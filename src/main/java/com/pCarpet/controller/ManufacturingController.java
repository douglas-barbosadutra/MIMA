package com.pCarpet.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.TimeDTO;
import com.pCarpet.services.TimeService;

@Controller
@RequestMapping("/Manufacturing")
public class ManufacturingController {

	TimeService timeService;
	
	@Autowired
	public ManufacturingController(TimeService timeService) {
		this.timeService = timeService;
	}
	
	@RequestMapping(value = "/showTime", method = RequestMethod.GET)
	public String showTime(HttpServletRequest request) {
		int idTask = Integer.parseInt(request.getParameter("idTask").toString());
		TaskDTO task = new TaskDTO();
		task.setId(idTask);
		List<TimeDTO> listaTempi = new ArrayList<>();
		listaTempi = timeService.getAllTempi(task);
		request.getSession().setAttribute("listaTempi", listaTempi);
		return "timeShow";
	}
}
