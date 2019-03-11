package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.TimeDTO;
import com.pCarpet.services.TimeService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Manufacturing")
public class ManufacturingController {

	TimeService timeService;
	
	@Autowired
	public ManufacturingController(TimeService timeService) {
		this.timeService = timeService;
	}
	
	@RequestMapping(value = "/showTime", method = RequestMethod.POST)
	public List<TimeDTO> showTime(@RequestBody TaskDTO task) {
		return timeService.getAllTempi(task);
	}
}
