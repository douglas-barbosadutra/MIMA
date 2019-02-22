package main.view;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class InsertInstructionView implements View{

	private String nomeIstruzione;
	private String durata;
	private String idTask;
	
	public void showResults(Request request) {

    }
	
	public void showOptions() {
        System.out.println("");
        System.out.println("Inserisci l'id del task a cui vuoi aggiungere l'istruzione:");
        idTask = getInput();
        System.out.println("Inserisci la nuova istruzione:");
        nomeIstruzione = getInput();
        System.out.println("Inserisci la durata:");
        durata = getInput();
    }
	
	public void submit() {
   	 Request request = new Request();
        request.put("nomeIstruzione", nomeIstruzione);
        request.put("durata", durata);
        request.put("idTask", idTask);
        MainDispatcher.getInstance().callAction("InsertInstruction", "doControl", request);
   }
	
	public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
