package main.controller;

import main.MainDispatcher;
import main.model.Session;
import main.service.MachineService;

public class UpdateMachineController implements Controller {

	private MachineService machineService;
	
	public UpdateMachineController() {
		
		machineService=new MachineService();
		
		
	}
	
	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
	
		String nome=request.get("nome").toString();
		String modello=request.get("modello").toString();
		int id=(int) request.get("id");
		
		if(id==0)
			MainDispatcher.getInstance().callView("MachineManagement", null);
		else {
			machineService.updateMachine(nome, modello, id, Session.getUserSession().getID());
			MainDispatcher.getInstance().callView("MachineManagement", null);
			
		}
	}

}
