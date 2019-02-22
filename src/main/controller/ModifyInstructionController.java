package main.controller;

import dto.InstructionDTO;
import main.MainDispatcher;
import main.service.InstructionService;

public class ModifyInstructionController implements Controller{
	
	private InstructionService istruzioneService;
	
	public ModifyInstructionController() {
		this.istruzioneService = new InstructionService();
	}
	
	@Override
	public void doControl(Request request) {
		if(request != null) {
			String nomeIstruzione = request.get("nomeIstruzione").toString();
			int durata = Integer.parseInt(request.get("durata").toString());
			int idTask = Integer.parseInt(request.get("idTask").toString());
			InstructionDTO istruzione = new InstructionDTO(nomeIstruzione, durata);
			istruzioneService.modifyIstruzione(istruzione, idTask);
			MainDispatcher.getInstance().callView("Instruction", null);
		}
		else {
			MainDispatcher.getInstance().callView("ModifyInstruction", request);
		}
	}

}
