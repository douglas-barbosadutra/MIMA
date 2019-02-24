package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class UpdateUserManagementView implements View{
	private String choice;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("---AGGIORNAMENTO PROFILO UTENTE ---");
		System.out.println("");
		System.out.println("Cosa vuoi aggiornare?");
		System.out.println("1) Email");
		System.out.println("2) Telefono");
		System.out.println("3) Username");
		System.out.println("4) Password");
		choice = getInput();
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
		request.put("choice", choice);
		MainDispatcher.getInstance().callAction("UpdateUser", "doControl", request);
	}

}
