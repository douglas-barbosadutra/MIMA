package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.services.MachineService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/Machine")
public class MachineController {
	
	private MachineService machineService;
	
	@Autowired
	public MachineController(MachineService ms) {
		machineService = ms;
	}
	
	@RequestMapping(value="/openInsertMachine")
	public String openInsertMachine(HttpServletRequest request) {
		return "machineInsert";
	}
	
	@RequestMapping(value="/insertMachine", method= RequestMethod.POST)
	public String insertUser(HttpServletRequest request) {
		
		String nome = request.getParameter("nome");
		String modello = request.getParameter("modello");
		
		MachineDTO machinedto = new MachineDTO(0, nome, modello, UserService.getUserSession().getId());
		
		machineService.insertMachine(machinedto);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/showMachine" , method= RequestMethod.GET)
	public String showMachine(HttpServletRequest request) {
		
		String showMachine = request.getParameter("showMachine");
		
		List<MachineDTO> machines = machineService.getAllMachines();
		
		request.getSession().setAttribute("machines_list", machines);
		request.getSession().setAttribute("showMachine", showMachine);
		
		return "machineShow";
	}

}
