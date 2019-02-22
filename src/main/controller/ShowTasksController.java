package main.controller;

import java.util.List;

import main.MainDispatcher;
import main.dto.TaskDTO;
import main.service.TaskService;

public class ShowTasksController implements Controller{
	private TaskService taskService;
	
	public ShowTasksController() {
		taskService = new TaskService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		int macchinario = (int) request.get("macchinario");
		
		if(macchinario != 0) {
			
			List<TaskDTO> tasks = taskService.getAllTasks(macchinario);
			request.put("tasks", tasks);
			MainDispatcher.getInstance().callView("ShowTasks", request);
		}
		
		else
			MainDispatcher.getInstance().callView("HomeUser", null);
	}

}
