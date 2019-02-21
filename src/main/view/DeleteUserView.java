package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class DeleteUserView implements View{
	private int id;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		
		System.out.println("---ELIMINA UTENTE---");
		System.out.println("ID (se non lo conosci inserisci 0 per tornare indietro):");
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
		MainDispatcher.getInstance().callAction("DeleteUser", "doControl", request);
	}

}
