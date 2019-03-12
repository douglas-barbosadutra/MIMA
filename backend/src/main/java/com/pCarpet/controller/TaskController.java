package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	private TaskService taskService;
	
	@Autowired
	public TaskController(TaskService ts) {
		taskService = ts;
	}
	
	@RequestMapping(value="/insertTask", method= RequestMethod.POST)
	public TaskDTO insertTask(@RequestBody TaskDTO taskdto) {
		return taskService.insertTask(taskdto);
	}
	
	@RequestMapping(value="/deleteTask" , method= RequestMethod.DELETE)
	public boolean deleteTask(@RequestParam(value="idTask") int idTask) {		
		return taskService.deleteTask(idTask);
	}
	
	@RequestMapping(value="/showTask" , method= RequestMethod.GET)
	public List<TaskDTO> showTask(@RequestParam(value="idMachine") int idMachine) {
		return taskService.getAllTasks(idMachine);
	}

}
