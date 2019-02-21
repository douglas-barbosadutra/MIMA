package main.controller;

import main.MainDispatcher;
import main.model.Session;
import main.service.MachineService;

public  class DeleteMachineController implements Controller {

	private MachineService machineService;
	
	public DeleteMachineController() {
		
		machineService=new MachineService();
		
		
	}
	
	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		int id=(int) request.get("id");
		if(id==0)
			MainDispatcher.getInstance().callView("MachineManagement", null);
		else {
			machineService.deleteMachine(id, Session.getUserSession().getID());
			MainDispatcher.getInstance().callView("MachineManagement", null);
			
			
			
		}
	}

}
