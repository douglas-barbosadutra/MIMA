package main.view;

import java.util.List;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Istruzione;

public class ShowListIstruzioneView implements View{
	
	
	@Override
	public void showResults(Request request) {
		List<Istruzione> istruzioni = (List<Istruzione>)request.get("istruzioni");
		System.out.println("----- Istruzioni -----");
		System.out.println("Nome	durata");
        istruzioni.forEach(istruzione -> System.out.println(istruzione.getNome() + " " + istruzione.getDurata()));
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
