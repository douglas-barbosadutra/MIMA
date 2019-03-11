package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.MachineService;


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
	public MachineDTO insertMachine(@RequestBody MachineDTO machinedto) {
		return machineService.insertMachine(machinedto);		
	}
	
	@RequestMapping(value="/deleteMachine" , method= RequestMethod.GET)
	public boolean deleteMachine(@RequestBody MachineDTO machinedto) {		
		return machineService.deleteMachine(machinedto.getId());
	}
	
	@RequestMapping(value="/showMachine" , method= RequestMethod.GET)
	public List<MachineDTO> showMachine(@RequestBody UserDTO user) {
		return (machineService.getAllMachinesByIdUser(user.getId()));

	}

}
