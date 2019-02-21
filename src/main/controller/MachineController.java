package main.controller;

import main.MainDispatcher;

public class MachineController implements Controller{
	
	@Override
	public void doControl(Request request) {
    	
    	int choice = (int) request.get("choice");
        switch (choice) {
            case 1:
               request.put("mode", "insert");
               break;
            case 2:
               request.put("mode", "update");
               break;
            case 3:
               request.put("mode", "delete");
               break;
            case 4:
               request.put("mode", "all");
               break;
        }
        MainDispatcher.getInstance().callView("Machine", request);
        
    }
	
}
