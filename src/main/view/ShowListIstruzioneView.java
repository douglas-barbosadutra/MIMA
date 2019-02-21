package main.view;

import java.util.List;

import dto.IstruzioneDTO;
import main.MainDispatcher;
import main.controller.Request;

public class ShowListIstruzioneView implements View{
	
	
	@Override
	public void showResults(Request request) {
		List<IstruzioneDTO> istruzioni = (List<IstruzioneDTO>)request.get("istruzioni");
		System.out.println("----- Istruzioni -----");
		System.out.println("Nome	durata");
        istruzioni.forEach(istruzione -> System.out.println(istruzione.getNomeIstruzione() + " " + istruzione.getDurata()));
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
		MainDispatcher.getInstance().callAction("Istruzione", "doControl", null);
	}

}
