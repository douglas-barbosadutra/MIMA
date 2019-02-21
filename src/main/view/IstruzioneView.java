package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Istruzione;
import main.service.IstruzioneService;

import java.util.List;
import java.util.Scanner;

public class IstruzioneView implements View{
	private IstruzioneService istruzioneService;
	String choice;
    
    public IstruzioneView () {
        this.istruzioneService = new IstruzioneService();
    }
    
    @Override
    public void showResults(Request request) {}
    
    @Override
    public void showOptions() {
    	System.out.println("");
    	System.out.println("-------MENU ISTRUZIONI-------");
        System.out.println("");
        System.out.println("1) Visualizza istruzioni di un determinato task");
        System.out.println("2) Inserisci una nuova istruzione");
        System.out.println("3) Modifica un istruzione");
        System.out.println("4) Elimina un istruzione");
        System.out.println("5) Torna indietro");
        this.choice = getInput();
    }
    
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    
    @Override
    public void submit() {
    	Request request = new Request();
    	request.put("choice", choice);
    	MainDispatcher.getInstance().callAction("Istruzione", "doControl", request);
    }
}
