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
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.services.TaskService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	public TaskController() {	}
	
	@PostMapping("/insertTask")
	public ResponseEntity<TaskDTO> insertTask(@RequestBody TaskDTO taskdto) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.insertTask(taskdto));
	}
	
	@DeleteMapping("/deleteTask")
	public ResponseEntity<Boolean> deleteTask(@RequestParam(value="idTask") int idTask) {		
		return ResponseEntity.status(HttpStatus.OK).body(taskService.deleteTask(idTask));
	}
	
	@GetMapping("/showTask")
	public ResponseEntity<List<TaskDTO>> showTask(@RequestParam(value="idMachine") int idMachine) {
		return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(idMachine));
	}

}
