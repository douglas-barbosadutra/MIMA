package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class ModifyIstruzioneView implements View{
	
	private String nomeIstruzione;
	private int idTask;
	private int durata;
	
	@Override
	public void showResults(Request request) {}

	@Override
	public void showOptions() {
		System.out.println("");
        System.out.println("Inserisci l'id del Task dal quale si vuole modificare l'istruzione:");
        idTask = Integer.parseInt(getInput());
        System.out.println("Inserisci il nome dell'istruzione da modificare1:");
        nomeIstruzione = getInput();
        System.out.println("Inserisci la nuova durata:");
        durata = Integer.parseInt(getInput());
	}

	@Override
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
	}

	@Override
	public void submit() {
		Request request = new Request();
        request.put("nomeIstruzione", nomeIstruzione);
        request.put("idTask", idTask);
        request.put("durata", durata);
        MainDispatcher.getInstance().callAction("ModifyIstruzione", "doControl", request);
		
	}

}
