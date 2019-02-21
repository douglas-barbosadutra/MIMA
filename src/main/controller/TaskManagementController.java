package main.controller;

import main.MainDispatcher;

public class TaskManagementController implements Controller{
	
	public TaskManagementController() {
		
	}
	
	@Override
	public void doControl(Request request) {
		MainDispatcher.getInstance().callView("TaskManagement", request);
	}
}
