package main.controller;

import main.service.MachineService;

import java.util.List;

import main.MainDispatcher;
import main.model.Machine;

public class ShowMachinesController implements Controller {
	private MachineService machineService;
	
	public ShowMachinesController() {
		machineService=new MachineService();
		
	}
	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		List<Machine> machines = machineService.getAllMachines();
		Request r=new Request();
		r.put("machines", machines);
		MainDispatcher.getInstance().callView("ShowMachines", r);
	}

}
