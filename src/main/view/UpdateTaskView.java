package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class UpdateTaskView implements View{
	private String descrizione;
	private int id;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---UPDATE TASK---");
		System.out.println("Descrizione: ");
		descrizione = getInput();
		System.out.println("ID (se non lo conosci inserisci 0 per tornare indietro): ");
		id = Integer.parseInt(getInput());
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		Request request = new Request();
		request.put("id", id);
		request.put("descrizione", descrizione);
		MainDispatcher.getInstance().callAction("UpdateTask", "doControl", request);
	}

}
