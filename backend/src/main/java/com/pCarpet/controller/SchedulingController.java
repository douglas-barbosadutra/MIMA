package com.pCarpet.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.services.MachineService;
import com.pCarpet.services.SchedulingService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/Scheduling")
public class SchedulingController {

	private SchedulingService schedulingService;
	private MachineService machineService;
	private static String name;

	@Autowired
	public SchedulingController(SchedulingService schedulingService, MachineService machineService) {
		this.schedulingService = schedulingService;
		this.machineService = machineService;
	}

	@RequestMapping(value = "/chooseScheduling", method = RequestMethod.GET)
	public String chooseMachine(HttpServletRequest request) {
		UserService.idMacchinario = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("idSchedulingScelto", UserService.idScheduling);
		return "homeUser";
	}

	@RequestMapping(value = "/insertScheduling", method = RequestMethod.POST)
	public String insertScheduling(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
		SchedulingDTO schedulingDTO = new SchedulingDTO(0, name, getTimestampByString(dataInizio),
				getTimestampByString(dataFine), UserService.idMacchinario);
		schedulingService.insertScheduling(schedulingDTO);
		return "homeUser";
	}

	@RequestMapping(value = "/deleteScheduling", method = RequestMethod.GET)
	public String deleteScheduling(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		schedulingService.deleteScheduling(id);
		return "homeUser";
	}

	@RequestMapping(value = "/showScheduling", method = RequestMethod.GET)
	public String showScheduling(HttpServletRequest request) {

			String showScheduling = request.getParameter("showScheduling");
			MachineDTO machineDTO = new MachineDTO();
			machineDTO.setId(UserService.idMacchinario);
			List<SchedulingDTO> scheduling = schedulingService.getAllScheduling(machineDTO);
			request.getSession().setAttribute("schedulingList", scheduling);
			request.getSession().setAttribute("showScheduling", showScheduling);
			return "schedulingList";
	
	}

	@RequestMapping(value = "/modifySchedulingOpen")
	public String modifySchedulingOpen(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		SchedulingController.name = name;
		UserService.idScheduling = id;
		return "schedulingModify";
	}

	@RequestMapping(value = "/modifyScheduling", method = RequestMethod.POST)
	public String modifyScheduling(HttpServletRequest request) {
		SchedulingDTO schedulingDTO = new SchedulingDTO();
		schedulingDTO.setId(UserService.idScheduling);
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
		schedulingDTO.setDataInizio(getTimestampByString(dataInizio));
		schedulingDTO.setDataFine(getTimestampByString(dataFine));
		schedulingDTO.setName(name);
		schedulingDTO.setIdMacchinario(UserService.idMacchinario);
		schedulingService.insertScheduling(schedulingDTO);
		return "homeUser";
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
}
