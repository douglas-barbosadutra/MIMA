package main.controller;

import main.service.MachineService;

import java.util.List;

import dto.MachineDTO;
import main.MainDispatcher;

public class ShowMachinesController implements Controller {
	private MachineService machineService;
	
	public ShowMachinesController() {
		machineService=new MachineService();
		
	}
	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		List<MachineDTO> machines = machineService.getAllMachines();
		Request r=new Request();
		r.put("machines", machines);
		MainDispatcher.getInstance().callView("ShowMachines", r);
	}

}
