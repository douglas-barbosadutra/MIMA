package main.controller;

import java.util.List;

import dto.InstructionDTO;
import main.MainDispatcher;
import main.service.InstructionService;

public class ShowInstructionController implements Controller{
	
	public void doControl(Request request) {
		if(request != null) {
			int idTask = 0;
			try {
				idTask = Integer.parseInt(request.get("choice").toString());
			}
			catch(NumberFormatException e) {
				System.out.println("Inserisci un idTask valido");
				MainDispatcher.getInstance().callView("ShowInstruction", null);
			}
			InstructionService istruzioneService = new InstructionService();
			List<InstructionDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
			Request result = new Request();
			result.put("istruzioni", istruzioni);
			MainDispatcher.getInstance().callView("ShowListInstruction", result);
		}
		else {
			MainDispatcher.getInstance().callView("ShowInstruction", null);
		}
	}
}
