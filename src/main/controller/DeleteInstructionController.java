package main.controller;

import main.MainDispatcher;
import main.service.InstructionService;

public class DeleteInstructionController implements Controller{
	
	private InstructionService istruzioneService;
	
	public DeleteInstructionController() {
		this.istruzioneService = new InstructionService();
	}
	
	@Override
	public void doControl(Request request) {
		if(request != null) {
			String nomeIstruzione = request.get("nomeIstruzione").toString();
			int idTask = 0;
			try {
				idTask = Integer.parseInt(request.get("idTask").toString());
			}
			catch(NumberFormatException e) {
				MainDispatcher.getInstance().callView("DeleteInstruction", null);
			}
			istruzioneService.deleteIstruzione(nomeIstruzione, idTask);
			MainDispatcher.getInstance().callView("Instruction", null);
		}
		else
	    	MainDispatcher.getInstance().callView("DeleteInstruction", request);
	}
}
