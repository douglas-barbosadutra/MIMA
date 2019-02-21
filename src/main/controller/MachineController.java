package main.controller;

import main.MainDispatcher;

public class MachineController implements Controller{
	
	@Override
	public void doControl(Request request) {
		
		if(request != null) {
			int choice = (int) request.get("choice");
	        switch (choice) {
	            case 1:
	            	MainDispatcher.getInstance().callView("InsertMachine", null);
	               break;
	            case 2:
	            	MainDispatcher.getInstance().callView("UpdateMachine", null);
	               break;
	            case 3:
	            	MainDispatcher.getInstance().callView("DeleteMachine", null);
	               break;
	            case 4:
	            	MainDispatcher.getInstance().callAction("ShowMachines","doControl", null);
	               break;
	        }
		}
    	
		else
			MainDispatcher.getInstance().callView("MachineManagement", null);
        
        
    }
	
}
