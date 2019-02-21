package main.controller;

import java.util.List;

import dto.IstruzioneDTO;
import main.MainDispatcher;
import main.service.IstruzioneService;

public class ShowIstruzioneController implements Controller{
	
	public void doControl(Request request) {
		if(request != null) {
			int idTask = 0;
			try {
				idTask = Integer.parseInt(request.get("choice").toString());
			}
			catch(NumberFormatException e) {
				System.out.println("Inserisci un idTask valido");
				MainDispatcher.getInstance().callView("ShowIstruzione", null);
			}
			IstruzioneService istruzioneService = new IstruzioneService();
			List<IstruzioneDTO> istruzioni = istruzioneService.getAllIstruzioni(idTask);
			Request result = new Request();
			result.put("istruzioni", istruzioni);
			MainDispatcher.getInstance().callView("ShowListIstruzione", result);
		}
		else {
			MainDispatcher.getInstance().callView("ShowIstruzione", null);
		}
	}
}
