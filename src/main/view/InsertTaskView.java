package main.view;

import java.sql.Timestamp;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class InsertTaskView implements View{
	
	private String descrizione;
	private int macchinario;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---INSERISCI TASK---");
		System.out.println("Descrizione: ");
		descrizione = getInput();
		System.out.println("ID macchinario (se non lo conosci inserisci 0 per tornare indietro): ");
		macchinario = Integer.parseInt(getInput());
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
		request.put("descrizione", descrizione);
		request.put("macchinario", macchinario);
		MainDispatcher.getInstance().callAction("InsertTask", "doControl", request);
	}

}
