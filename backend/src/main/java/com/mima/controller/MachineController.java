package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.MachineService;


@CrossOrigin(value="*")
@RestController
@RequestMapping("/Machine")
public class MachineController {
	
	@Autowired
	private MachineService machineService;
	
	public MachineController() {	}
	
	@PostMapping("/insertMachine")
	public ResponseEntity<MachineDTO> insertMachine(@RequestBody MachineDTO machinedto) {
		
		MachineDTO machine = machineService.insertMachine(machinedto);
		
		if(machine != null)
			return ResponseEntity.status(HttpStatus.OK).body(machine);
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}
	
	@DeleteMapping("/deleteMachine")
	public ResponseEntity<Boolean> deleteMachine(@RequestParam(value="idMachine") int idMachine) {		
		return ResponseEntity.status(HttpStatus.OK).body(machineService.deleteMachine(idMachine));
	}
	
	@GetMapping("/showMachine")
	public ResponseEntity<List<MachineDTO>> showMachine(@RequestParam(value="idUser") int idUser) {
		return ResponseEntity.status(HttpStatus.OK).body(machineService.getAllMachinesByIdUser(idUser));

	}

}
