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
		System.out.println("3)Modifica Task");
		System.out.println("4)Elimina Task");
		System.out.println("5)Gestione Istruzioni");
		System.out.println("6)Torna Indietro");
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
		if(choice<1 || choice>6)
			MainDispatcher.getInstance().callAction("TaskManagement","doControl",null);
		else if(choice==5)
			MainDispatcher.getInstance().callAction("Instruction","doControl",null);
		else if(choice==6)
			MainDispatcher.getInstance().callAction("Home","doControl",null);
		else {
			Request request=new Request();
			request.put("choice", choice);
			MainDispatcher.getInstance().callAction("Task","doControl",request);
		}
	}
	
}
