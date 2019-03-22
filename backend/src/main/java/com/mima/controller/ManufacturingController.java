package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.TimeDTO;
import com.pCarpet.services.TimeService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Manufacturing")
public class ManufacturingController {

	@Autowired
	TimeService timeService;
	
	@Autowired
	public ManufacturingController() {	}
	
	@GetMapping("/showTime")
	public ResponseEntity<List<TimeDTO>> showTime(@RequestParam(value="idTask") int idTask) {
		return ResponseEntity.status(HttpStatus.OK).body(timeService.getAllTempi(idTask));
	}
}
