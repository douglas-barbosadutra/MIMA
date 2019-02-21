package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class InsertUserView implements View{
	private String username;
	private String password;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---INSERISCI UTENTE---");
		System.out.println("Username: ");
		username = getInput();
		System.out.println("Password: ");
		password = getInput();
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
		
		request.put("username", username);
		request.put("password", password);
		MainDispatcher.getInstance().callAction("InsertUser", "doControl", request);
	}

}
