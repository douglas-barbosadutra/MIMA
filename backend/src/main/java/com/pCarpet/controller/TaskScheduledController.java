package com.pCarpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.OperationSchedulingDTO;
import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.services.TaskScheduledService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/TaskScheduled")
public class TaskScheduledController {

	TaskScheduledService taskScheduledService;

	@Autowired
	public TaskScheduledController(TaskScheduledService taskScheduledService) {
		this.taskScheduledService = taskScheduledService;
	}

	@RequestMapping(value = "/insertOperazionScheduling", method = RequestMethod.POST)
	public String insertOperazionScheduling(@RequestBody OperationSchedulingDTO osDTO) {
		taskScheduledService.insertScheduledRelations(osDTO);
		return "";
	}

	@RequestMapping(value = "/insertTaskScheduled", method = RequestMethod.POST)
	public TaskScheduledDTO insertTaskScheduled(@RequestBody TaskScheduledDTO taskScheduled) {
		return taskScheduledService.insertTaskScheduled(taskScheduled);
	}
	
	@RequestMapping(value = "/deleteTaskScheduled", method = RequestMethod.DELETE)
	public boolean deleteTaskScheduled(@RequestParam(value="idTaskScheduled") int idTaskScheduled) {
		return taskScheduledService.deleteTaskScheduled(idTaskScheduled);
	}

	@RequestMapping(value = "/showTaskScheduled", method = RequestMethod.GET)
	public TaskScheduledDTO getTaskScheduledRoot(@RequestBody SchedulingDTO scheduling) {
		return taskScheduledService.getTaskScheduledRoot(scheduling);
	}

}
