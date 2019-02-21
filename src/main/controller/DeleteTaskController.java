package main.controller;

import main.MainDispatcher;
import main.service.TaskService;

public class DeleteTaskController implements Controller{
	private TaskService taskService;
	
	public DeleteTaskController() {
		taskService = new TaskService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		int id = (int) request.get("id");
		
		if(id != 0)
			taskService.deleteTask(id);
		
		MainDispatcher.getInstance().callView("TaskManagement", null);
	}

}
