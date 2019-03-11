package com.pCarpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.services.OperationSchedulingService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/OperationScheduling")
public class OperationSchedulingController {

	OperationSchedulingService operationSchedulingService;

	@Autowired
	public OperationSchedulingController(OperationSchedulingService operationSchedulingService) {
		this.operationSchedulingService = operationSchedulingService;
	}
	
	@RequestMapping(value = "/insertOperazionScheduling", method = RequestMethod.POST)
	public void insertOperazionScheduling(@RequestBody TaskScheduledDTO father, @RequestBody TaskScheduledDTO child) {
		operationSchedulingService.insertOperationScheduling(father, child);
	}
	
	@RequestMapping(value = "/deleteOperationScheduling", method = RequestMethod.POST)
	public boolean deleteOperationScheduling() {
		int id = 0;
		return operationSchedulingService.deleteOperationScheduling(id);
	}
	
	@RequestMapping(value = "/showOperazionScheduling", method = RequestMethod.POST)
	public TaskScheduledDTO showOperazionScheduling(@RequestBody SchedulingDTO scheduling) {
		TaskScheduledDTO temp = new TaskScheduledDTO();
		return temp;
	}
	
}
