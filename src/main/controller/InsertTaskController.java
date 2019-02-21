package main.controller;

import main.MainDispatcher;
import main.service.TaskService;

public class InsertTaskController implements Controller{
	private TaskService taskService;
	
	public InsertTaskController() {
		taskService = new TaskService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		String descrizione = request.get("descrizione").toString();
		int macchinario = (int) request.get("macchinario");
		
		if(macchinario != 0)
			taskService.insertTask(descrizione, macchinario);
		MainDispatcher.getInstance().callView("TaskManagement", null);
	}

}
