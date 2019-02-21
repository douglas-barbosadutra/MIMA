package main.controller;

import main.MainDispatcher;
import main.model.Istruzione;
import main.service.IstruzioneService;

public class InsertIstruzioneController implements Controller{

	private IstruzioneService istruzioneService;
	
	public InsertIstruzioneController() {
		this.istruzioneService = new IstruzioneService();
	}
	
	@Override
	public void doControl(Request request) {
		if(request != null) {
			String nomeIstruzione = request.get("nomeIstruzione").toString();
			int durata = Integer.parseInt(request.get("durata").toString());
			int idTask = Integer.parseInt(request.get("idTask").toString());
			Istruzione istruzione = new Istruzione(nomeIstruzione, durata);
			if(istruzioneService.insertIstruzione(istruzione, idTask))
				MainDispatcher.getInstance().callView("Istruzione", null);
			else {
				MainDispatcher.getInstance().callView("Istruzione", null);
			}
			
		}
		else
	    	MainDispatcher.getInstance().callView("InsertIstruzione", request);
	}
}
