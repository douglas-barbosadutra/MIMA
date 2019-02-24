package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class InsertUserView implements View{
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String rank;

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
		System.out.println("Nome: ");
		name = getInput();
		System.out.println("Cognome: ");
		surname = getInput();
		System.out.println("Email: ");
		email = getInput();
		System.out.println("Telefono: ");
		phone = getInput();
		System.out.println("E' un amministratore? (Y/N): ");
		rank = getInput();		
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
		request.put("name", name);
		request.put("surname", surname);
		request.put("email", email);
		request.put("phone", phone);
		request.put("rank", rank);
		MainDispatcher.getInstance().callAction("InsertUser", "doControl", request);
	}

}
