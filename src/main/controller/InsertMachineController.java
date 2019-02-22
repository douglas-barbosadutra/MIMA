package main.controller;

import main.MainDispatcher;
import main.service.MachineService;
import main.service.UserService;

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
		
		machineService.insertMachine(nome, modello, UserService.getUserSession().getID());
		MainDispatcher.getInstance().callView("MachineManagement", null);
		
	}

}
