package main.view;

import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;

public class UpdateMachineView implements View {

	private String nome;
	private String modello;
	private int id;
	
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("Inserisci le nuove caratteristiche del macchinario: ");
		System.out.println("Nome: ");
		nome=getInput();
		System.out.println("Modello: ");
		modello=getInput();
		System.out.println("ID(Se non lo conosci per tornare alla schermata principale inserisci 0 )");
		id=Integer.parseInt(getInput());
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
		request.put("id", id);
		MainDispatcher.getInstance().callAction("UpdateMachine", "doControl", request);
	}

}
