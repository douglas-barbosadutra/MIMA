package main.controller;

import dto.InstructionDTO;
import main.MainDispatcher;
import main.service.InstructionService;

public class InsertInstructionController implements Controller{

	private InstructionService istruzioneService;
	
	public InsertInstructionController() {
		this.istruzioneService = new InstructionService();
	}
	
	@Override
	public void doControl(Request request) {
		if(request != null) {
			String nomeIstruzione = request.get("nomeIstruzione").toString();
			int idTask = 0;
			int durata = 0;
			try {
				idTask = Integer.parseInt(request.get("idTask").toString());
				durata = Integer.parseInt(request.get("durata").toString());
			}
			catch(NumberFormatException e) {
				System.out.println("Inserisci dei valori validi");
				MainDispatcher.getInstance().callView("InsertInstruction", null);
			}
			InstructionDTO istruzione = new InstructionDTO(nomeIstruzione, durata);
			istruzioneService.insertIstruzione(istruzione, idTask);
			MainDispatcher.getInstance().callView("Instruction", null);
		}
		else
	    	MainDispatcher.getInstance().callView("InsertInstruction", request);
	}
}
