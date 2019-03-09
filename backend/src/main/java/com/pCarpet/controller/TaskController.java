package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.services.MachineService;
import com.pCarpet.services.TaskService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/Task")
public class TaskController {
	
	private TaskService taskService;
	private MachineService machineService;
	
	@Autowired
	public TaskController(TaskService ts, MachineService ms) {
		taskService = ts;
		machineService = ms;
	}
	
	@RequestMapping(value="/chooseMachine" , method= RequestMethod.GET)
	public String chooseMachine(HttpServletRequest request) {		
		
		UserService.idMacchinario = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("idMacchinarioScelto", UserService.idMacchinario);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/openInsertTask")
	public String openInsertTask(HttpServletRequest request) {
		if(UserService.idMacchinario == 0) {
			
			List<MachineDTO> machines = machineService.getAllMachines();
			
			request.getSession().setAttribute("machines_list", machines);
			request.getSession().setAttribute("showMachine", "choose");
			
			return "machineShow";
			
		}else {
			return "taskInsert";
		}
	}
	
	@RequestMapping(value="/insertTask", method= RequestMethod.POST)
	public String insertTask(HttpServletRequest request) {
		
		String descrizione = request.getParameter("descrizione");
		
		TaskDTO taskdto = new TaskDTO(0, descrizione, UserService.idMacchinario);
		
		taskService.insertTask(taskdto);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/deleteTask" , method= RequestMethod.GET)
	public String deleteTask(HttpServletRequest request) {		
		
		int id = Integer.parseInt(request.getParameter("id"));
		taskService.deleteTask(id);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/showTask" , method= RequestMethod.GET)
	public String showTask(HttpServletRequest request) {
		
		if(UserService.idMacchinario == 0) {
		
			List<MachineDTO> machines = machineService.getAllMachines();
			
			request.getSession().setAttribute("machines_list", machines);
			request.getSession().setAttribute("showMachine", "choose");
			
			return "machineShow";
		
		}else{
			
			String showTask = request.getParameter("showTask");
			
			List<TaskDTO> tasks = taskService.getAllTasks(UserService.idMacchinario);
			
			request.getSession().setAttribute("taskList", tasks);
			request.getSession().setAttribute("showTask", showTask);

			return "taskShow";
		}
		
	}

}
