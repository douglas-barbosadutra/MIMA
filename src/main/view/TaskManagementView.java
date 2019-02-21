package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class TaskManagementView implements View{

	private int choice;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("");
		System.out.println("---GESTIONE TASK");
		System.out.println("");
		System.out.println("1)Visualizza Tasks");
		System.out.println("2)Inserisci Task");
		System.out.println("3)Torna Indietro");
		choice=Integer.parseInt(getInput());
		
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		return scanner.nextLine();
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		if(choice<1 || choice>3)
			MainDispatcher.getInstance().callView("TaskManagement",null);
		else if(choice==3)
			MainDispatcher.getInstance().callView("HomeUser",null);
		else {
			Request request=new Request();
			request.put("choice", choice);
			MainDispatcher.getInstance().callAction("Task","doControl",request);
		}
	}
	
	
	
}
