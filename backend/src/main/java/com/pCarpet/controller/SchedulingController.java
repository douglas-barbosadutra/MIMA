package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.services.SchedulingService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Scheduling")
public class SchedulingController {

	private SchedulingService schedulingService;

	@Autowired
	public SchedulingController(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}

	@PostMapping("/insertScheduling")
	public ResponseEntity<SchedulingDTO> insertScheduling(@RequestBody SchedulingDTO schedulingDTO) {
		
		return ResponseEntity.status(HttpStatus.OK).body(schedulingService.insertScheduling(schedulingDTO));
	}

	@DeleteMapping("/deleteScheduling")
	public ResponseEntity<Boolean> deleteScheduling(@RequestParam(value="idScheduling") int idScheduling) {
		return ResponseEntity.status(HttpStatus.OK).body(schedulingService.deleteScheduling(idScheduling));
	}

	@GetMapping("/showScheduling")
	public ResponseEntity<List<SchedulingDTO>> showScheduling(@RequestParam(value="idMachine") int idMachine) {
		return ResponseEntity.status(HttpStatus.OK).body(schedulingService.getAllScheduling(idMachine));
	}
	
	@PutMapping("/updateScheduling")
	public ResponseEntity<SchedulingDTO> updateScheduling(@RequestBody SchedulingDTO schedulingDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(schedulingService.insertScheduling(schedulingDTO));
	}

}
