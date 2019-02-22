package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Instruction;
import main.service.InstructionService;

import java.util.List;
import java.util.Scanner;

public class InstructionView implements View{
	private InstructionService istruzioneService;
	String choice;
    
    public InstructionView () {
        this.istruzioneService = new InstructionService();
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
    	MainDispatcher.getInstance().callAction("Instruction", "doControl", request);
    }
}
