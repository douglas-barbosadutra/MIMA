package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class ShowInstructionView implements View{

	private String choice;
	
	@Override
	public void showResults(Request request) {
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci l'id del task di cui si vuole conoscere le istruzioni");
        System.out.println("IdTask:");
        choice = getInput();
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
    	MainDispatcher.getInstance().callAction("ShowInstruction", "doControl", request);
	}
	
}
