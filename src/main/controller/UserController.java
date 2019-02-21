package main.controller;

import main.MainDispatcher;

public class UserController implements Controller{

	@Override
	public void doControl(Request request) {
		
		if(request != null) {
			
			int choice = (int) request.get("choice");
	        switch (choice) {
	            case 1:
	            	MainDispatcher.getInstance().callView("InsertUser", null);
	               break;
	            case 2:
	            	MainDispatcher.getInstance().callView("DeleteUser", null);
	               break;
	            case 3:
	            	MainDispatcher.getInstance().callAction("ShowUsers","doControl", request);
	               break;
	        }
		}
    	
		else
			MainDispatcher.getInstance().callView("HomeAdmin", null);
        
    }
	
}
