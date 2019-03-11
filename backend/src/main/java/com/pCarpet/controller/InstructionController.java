package com.pCarpet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.services.InstructionService;
import com.pCarpet.services.TaskService;
import com.pCarpet.services.UserService;

@Controller
@RequestMapping("/Instruction")
public class InstructionController {
	
	private TaskService taskService;
	private InstructionService istruzioneService;
	
	@Autowired
	public InstructionController(TaskService ts, InstructionService is) {
		taskService = ts;
		istruzioneService = is;
	}
	
	@RequestMapping(value="/chooseTask" , method= RequestMethod.GET)
	public String chooseTask(HttpServletRequest request) {		
		
		UserService.idTask = Integer.parseInt(request.getParameter("id"));
		request.getSession().setAttribute("idTaskScelto", UserService.idTask);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/insertInstructionOpen")
	public String insertInstructionOpen(HttpServletRequest request) {
		
		if(UserService.idTask == 0) {
			
			List<TaskDTO> tasks = taskService.getAllTasks(UserService.idMacchinario);
			
			request.getSession().setAttribute("taskList", tasks);
			request.getSession().setAttribute("showTask", "choose");
			
			return "taskShow";
		
		}else{
			
			return "instructionInsert";
		}
	}
	
	@RequestMapping(value="/insertInstruction", method= RequestMethod.POST)
	public String insertTask(HttpServletRequest request) {
		
		String nomeIstruzione = request.getParameter("nome");
		int durata = Integer.parseInt(request.getParameter("durata"));
		String codice = request.getParameter("codice");
		
		InstructionDTO istruzionedto = new InstructionDTO(0, durata, codice, nomeIstruzione, UserService.idTask);
		
		istruzioneService.insertIstruzione(istruzionedto);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/deleteInstruction" , method= RequestMethod.GET)
	public String deleteInstruction(HttpServletRequest request) {		
		
		int id = Integer.parseInt(request.getParameter("id"));
		istruzioneService.deleteIstruzione(id);
		
		return "homeUser";
	}
	
	@RequestMapping(value="/showInstruction" , method= RequestMethod.GET)
	public String showInstruction(HttpServletRequest request) {
		
		if(UserService.idTask == 0) {
			
			List<TaskDTO> tasks = taskService.getAllTasks(UserService.idMacchinario);
			
			request.getSession().setAttribute("taskList", tasks);
			request.getSession().setAttribute("showTask", "choose");
			
			return "taskShow";
		
		}else{
			
			String showInstruction = request.getParameter("showInstruction");
			
			TaskDTO taskdto = new TaskDTO();
			taskdto.setId(UserService.idTask);
			
			List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(taskdto);
			
			request.getSession().setAttribute("listaIstruzioni", istruzioni);
			request.getSession().setAttribute("showInstruction", showInstruction);

			return "instructionShow";
		}
		
	}

}
