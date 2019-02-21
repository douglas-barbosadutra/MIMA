package main.view;

import java.util.List;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;
import main.model.User;
import main.service.UserService;


public class UserView implements View {

	private UserService userService;
    private String mode;
    
    public UserView() {
    	this.userService = new UserService();
    	this.mode = "all";
    }

    public void showResults(Request request) {
    	this.mode  = (String) request.get("mode");
    }


    public void showOptions() {
    	
    	switch (mode) {
	        case "all":
                List<User> users = userService.getAllUsers();               
                System.out.println("----- Utenti disponibili -----");
                System.out.println();
                users.forEach(user -> System.out.println(user));                 
	            break;
	            
	        case "insert":   	
	            System.out.println("");
	            System.out.println("Inserisci username:");
	            String nomeUtente = getInput();
	            System.out.println("Inserisci password:");
	            String password = getInput();  
	            userService.insertUser(nomeUtente, password);  
	            break;
	            
	        case "delete":   	
	            System.out.println("");
	            System.out.println("Inserisci l'ID dell'utente da eliminare:");
	            int idUser = Integer.parseInt(getInput());
	            userService.deleteUser(idUser); 
	            break;
    	}

    }

    public void submit() {
         MainDispatcher.getInstance().callAction("Home", "doControl", null);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
