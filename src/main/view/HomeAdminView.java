package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class HomeAdminView implements View {

    private int choice;

    public void showResults(Request request) {

    }
    

    public void showOptions() {
        System.out.println("");
        System.out.println("-------MENU AMMINISTRATORE-------");
        System.out.println("");
        System.out.println("1) Inserisci utente");
        System.out.println("2) Elimina utente");
        System.out.println("3) Visualizza utenti");
        System.out.println("4) Logout");
        this.choice = Integer.parseInt(getInput());
    }
    

    public void submit() {
        if (choice < 1 || choice > 4)
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        else if (choice == 4)
            MainDispatcher.getInstance().callAction("Login", "doControl", null);
        else {
            Request request = new Request();
            request.put("choice", choice);
            MainDispatcher.getInstance().callAction("User", "doControl", request);
        }
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}