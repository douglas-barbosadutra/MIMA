package main.controller;

import main.MainDispatcher;

public class TaskController implements Controller{
	
	@Override
	public void doControl(Request request) {
		int choice=(int)request.get("choice");
		
		if(choice==1)
			request.put("mode", "all");
		else
			request.put("mode","insert");
		
		MainDispatcher.getInstance().callView("Task", request);
	}
	
	


}
