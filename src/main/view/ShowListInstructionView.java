package main.view;

import java.util.List;

import main.MainDispatcher;
import main.controller.Request;
import main.dto.InstructionDTO;

public class ShowListInstructionView implements View{
	
	
	@Override
	public void showResults(Request request) {
		List<InstructionDTO> istruzioni = (List<InstructionDTO>)request.get("istruzioni");
		System.out.println("----- Istruzioni -----");
		System.out.println("");
        istruzioni.forEach(istruzione -> System.out.println(istruzione.toString()));
	}

	@Override
	public void showOptions() {
		
	}

	@Override
	public String getInput() {
		return null;
	}

	@Override
	public void submit() {
		MainDispatcher.getInstance().callAction("Instruction", "doControl", null);
	}

}
