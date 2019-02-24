package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class UpdateUserView implements View{
	private String attribute;
	private String value;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		attribute = request.get("attribute").toString();
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("Inserisci il nuovo valore:");
		value = getInput();
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
		request.put("attribute", attribute);
		request.put("value", value);
		MainDispatcher.getInstance().callAction("UpdateUserInfo", "doControl", request);
	}

}
