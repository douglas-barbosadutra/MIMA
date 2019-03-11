package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.services.MachineService;
import com.pCarpet.services.UserService;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/Machine")
public class MachineController {
	
	private MachineService machineService;
	
	@Autowired
	public MachineController(MachineService ms) {
		machineService = ms;
	}
	
	@RequestMapping(value="/insertMachine", method= RequestMethod.POST)
	public String insertUser(HttpServletRequest request) {
		
		String nome = request.getParameter("nome");
		String modello = request.getParameter("modello");
		
		MachineDTO machinedto = new MachineDTO(0, nome, modello, UserService.getUserSession().getId());
		
		machineService.insertMachine(machinedto);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/deleteMachine" , method= RequestMethod.GET)
	public String deleteMachine(HttpServletRequest request) {		
		
		int id = Integer.parseInt(request.getParameter("id"));
		machineService.deleteMachine(id);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/showMachine" , method= RequestMethod.GET)
	public List<MachineDTO> showMachine(HttpServletRequest request) {
		int idUser = (int)request.getAttribute("idUser");
		return (machineService.getAllMachinesByIdUser(idUser));
	}

}
