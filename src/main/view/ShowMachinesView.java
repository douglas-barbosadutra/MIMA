package main.view;

import java.util.List;

import dto.MachineDTO;
import main.MainDispatcher;
import main.controller.Request;

public class ShowMachinesView implements View {

	private List<MachineDTO> machines;
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		machines=(List<MachineDTO>) request.get("machines");
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---Macchinari Disponibili---");
		System.out.println(" ");
		machines.forEach(machine-> System.out.println(machine));
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		MainDispatcher.getInstance().callAction("Machine", "doControl", null);
	}

}
