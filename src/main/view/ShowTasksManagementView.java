package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class ShowTasksManagementView implements View{
	private int macchinario;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("ID macchinario (se non lo conosci inserci 0 per tornare indietro): ");
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
		request.put("macchinario", macchinario);
		MainDispatcher.getInstance().callAction("ShowTasks", "doControl", request);
	}

}
