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
			int idTask = 0;
			int durata = 0;
			try {
				idTask = Integer.parseInt(request.get("idTask").toString());
				durata = Integer.parseInt(request.get("durata").toString());
			}
			catch(NumberFormatException e) {
				System.out.println("Inserisci dei valori validi");
				MainDispatcher.getInstance().callView("InsertIstruzione", null);
			}
			Istruzione istruzione = new Istruzione(nomeIstruzione, durata);
			istruzioneService.insertIstruzione(istruzione, idTask);
			MainDispatcher.getInstance().callView("Istruzione", null);
		}
		else
	    	MainDispatcher.getInstance().callView("InsertIstruzione", request);
	}
}
