package main.controller;

import main.MainDispatcher;
import main.service.TaskService;

public class UpdateTaskController implements Controller{
	private TaskService taskService;
	
	public UpdateTaskController() {
		taskService = new TaskService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		String descrizione = request.get("descrizione").toString();
		int id = (int) request.get("id");
		
		if(id != 0)
			taskService.updateTask(descrizione, id);
		
		MainDispatcher.getInstance().callView("TaskManagement", null);
	}

}
