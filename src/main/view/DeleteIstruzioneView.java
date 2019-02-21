package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class DeleteIstruzioneView implements View{

	private String nomeIstruzione;
	private String idTask;
	
	public void showResults(Request request) {

    }

    public void showOptions() {
        System.out.println("");
        System.out.println("Inserisci l'id del Task dal quale si vuole eliminare l'istruzione:");
        idTask = getInput();
        System.out.println("Inserisci il nome dell'istruzione da eliminare:");
        nomeIstruzione = getInput();
    }

    public void submit() {
    	 Request request = new Request();
         request.put("nomeIstruzione", nomeIstruzione);
         request.put("idTask", idTask);
         MainDispatcher.getInstance().callAction("DeleteIstruzione", "doControl", request);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
