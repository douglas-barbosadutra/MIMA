package main.controller;

import main.MainDispatcher;

public class TaskController implements Controller{
	
	@Override
	public void doControl(Request request) {
		
		if(request != null) {
			int choice=(int)request.get("choice");
			
			switch(choice) {
				
				case 1:{
					MainDispatcher.getInstance().callView("ShowTasksManagement", null);
				} break;
				
				case 2:{
					MainDispatcher.getInstance().callView("InsertTask", null);
				} break;
				
				case 3:{
					MainDispatcher.getInstance().callView("UpdateTask", null);
				} break;
				
				case 4:{
					MainDispatcher.getInstance().callView("DeleteTask", null);
				} break;
			}
		}
		
		else
			MainDispatcher.getInstance().callView("TaskManagement", null);
		
	}

}
