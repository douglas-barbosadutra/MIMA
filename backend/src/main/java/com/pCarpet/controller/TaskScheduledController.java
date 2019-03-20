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

import com.pCarpet.dto.OperationSchedulingDTO;
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

	@PostMapping("/insertOperationScheduling")
	public ResponseEntity<Boolean> insertOperazionScheduling(@RequestBody OperationSchedulingDTO osDTO) {
		taskScheduledService.insertScheduledRelations(osDTO);
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}

	@PostMapping("/insertTaskScheduled")
	public ResponseEntity<TaskScheduledDTO> insertTaskScheduled(@RequestBody TaskScheduledDTO taskScheduled) {
		return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.insertTaskScheduled(taskScheduled));
	}
	
	@DeleteMapping("/deleteTaskScheduled")
	public ResponseEntity<Boolean> deleteTaskScheduled(@RequestParam(value="idTaskScheduled") int idTaskScheduled) {
		return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.deleteTaskScheduled(idTaskScheduled));
	}

	@GetMapping("/showTaskScheduled")
	public ResponseEntity<List<TaskScheduledDTO>> showTaskScheduled(@RequestParam(value="idScheduling") int idScheduling) {
		return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.getTaskScheduling(idScheduling));
	}
	
	@GetMapping("/showOperationScheduling")
	public ResponseEntity<List<OperationSchedulingDTO>> showOperationScheduling(@RequestParam(value="idScheduling") int idScheduling) {
		return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.getOperationScheduling(idScheduling));
	}
	
	@GetMapping("/insertOutput")
	public ResponseEntity<Boolean> insertOutput(@RequestParam(value="idItem") int idItem, @RequestParam(value="idOperationScheduling") int idOperationScheduling) {
		
		return ResponseEntity.status(HttpStatus.OK).body(taskScheduledService.insertOutput(idItem, idOperationScheduling));
	}
}
