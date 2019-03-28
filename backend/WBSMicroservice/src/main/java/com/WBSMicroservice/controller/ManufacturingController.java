package com.WBSMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WBSMicroservice.dto.TimeDTO;
import com.WBSMicroservice.services.TimeService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Manufacturing")
public class ManufacturingController {

	@Autowired
	TimeService timeService;
	
	public ManufacturingController() {	}
	
	@GetMapping("/showTime")
	public ResponseEntity<List<TimeDTO>> showTime(@RequestParam(value="idTask") int idTask) {
		return ResponseEntity.status(HttpStatus.OK).body(timeService.getAllTempi(idTask));
	}
}
