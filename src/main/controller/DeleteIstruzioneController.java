package main.controller;

import main.MainDispatcher;
import main.service.IstruzioneService;

public class DeleteIstruzioneController implements Controller{
	
	private IstruzioneService istruzioneService;
	
	public DeleteIstruzioneController() {
		this.istruzioneService = new IstruzioneService();
	}
	
	@Override
	public void doControl(Request request) {
		if(request != null) {
			String nomeIstruzione = request.get("nomeIstruzione").toString();
			int idTask = Integer.parseInt(request.get("idTask").toString());
			if(istruzioneService.deleteIstruzione(nomeIstruzione, idTask)) {
				MainDispatcher.getInstance().callView("Istruzione", null);
			}
			else {
				MainDispatcher.getInstance().callView("Istruzione", null);
			}
		}
		else
	    	MainDispatcher.getInstance().callView("DeleteIstruzione", request);
	}
}
