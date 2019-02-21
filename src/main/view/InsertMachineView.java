package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class InsertMachineView implements View {

	private String nome;
	private String modello;
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci le caratteristiche del macchinario: ");
		System.out.println("Nome: ");
		nome=getInput();
		System.out.println("Modello: ");
		modello=getInput();
		
		
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
		Request request=new Request();
		request.put("nome", nome);
		request.put("modello", modello);
		MainDispatcher.getInstance().callAction("InsertMachine", "doControl", request);
	
	}

	
}
