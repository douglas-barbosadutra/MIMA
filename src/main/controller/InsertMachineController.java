package main.controller;

import main.MainDispatcher;
import main.model.Session;
import main.service.MachineService;

public class InsertMachineController implements Controller {

	private MachineService machineService;
	
	public InsertMachineController() {
		
		this.machineService = new MachineService();
		
	}
	
	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		String nome=request.get("nome").toString();
		String modello=request.get("modello").toString();
		
		machineService.insertMachine(nome, modello, Session.getUserSession().getID());
		MainDispatcher.getInstance().callView("MachineManagement", null);
		
	}

}
