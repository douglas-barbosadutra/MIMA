package main.view;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class InsertIstruzioneView implements View{

	private String nomeIstruzione;
	private int durata;
	private int idTask;
	
	public void showResults(Request request) {

    }
	
	public void showOptions() {
        System.out.println("");
        System.out.println("Inserisci l'id del task a cui vuoi aggiungere l'istruzione:");
        idTask = Integer.parseInt(getInput());
        System.out.println("Inserisci la nuova istruzione:");
        nomeIstruzione = getInput();
        System.out.println("Inserisci la durata:");
        durata = Integer.parseInt(getInput());
    }
	
	public void submit() {
   	 Request request = new Request();
        request.put("nomeIstruzione", nomeIstruzione);
        request.put("durata", durata);
        request.put("idTask", idTask);
        MainDispatcher.getInstance().callAction("InsertIstruzione", "doControl", request);
   }
	
	public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
