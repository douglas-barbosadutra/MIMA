package main.view;

import java.util.List;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Machine;
import main.model.Session;
import main.model.User;
import main.service.MachineService;

public class MachineView implements View {
    
    private MachineService machineService;
    private String mode;
    
    public MachineView() {
    	machineService = new MachineService();
    	this.mode = "all";
    }

    public void showResults(Request request) {
    	this.mode  = (String) request.get("mode");
    }


    public void showOptions() {
    	
    	switch (mode) {
	        case "all":
	            List<Machine> machines = machineService.getAllMachines();               
	            System.out.println("----- Macchinari disponibili -----");
	            System.out.println();
	            machines.forEach(machine -> System.out.println(machine));   
	            break;
	            
	        case "insert":   	
	            System.out.println("");
	            System.out.println("Inserisci il nome del macchinario:");
	            String nomeMacchinario = getInput();
	            System.out.println("Inserisci il modello del macchinario:");
	            String modelloMacchinario = getInput();
	            machineService.insertMachine(nomeMacchinario, modelloMacchinario, Session.getUserSession().getID());
	            break;
	            
	        case "delete":   	
	        	System.out.println("");
	            System.out.println("Inserisci l'ID del macchinario da eliminare:");
	            int idMacchinario = Integer.parseInt(getInput());
	            machineService.deleteMachine(idMacchinario, Session.getUserSession().getID());
	            break;
	            
	        case "update":   	
	        	System.out.println("");
	            System.out.println("Inserisci l'ID del macchinario da modificare:");
	            int idMacchinario1 = Integer.parseInt(getInput());
	            System.out.println("Inserisci il nuovo nome del macchinario:");
	            String nomeMacchinario1 = getInput();
	            System.out.println("Inserisci il nuovo modello del macchinario:");
	            String modelloMacchinario1 = getInput();
	            machineService.updateMachine(nomeMacchinario1, modelloMacchinario1, idMacchinario1, Session.getUserSession().getID());
	            break;
    	}
    	
    }

    public void submit() {         
         MainDispatcher.getInstance().callAction("MachineManagement", "doControl", null);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}

