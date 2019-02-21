package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class MachineManagementView implements View {

    private int choice;

    public void showResults(Request request) {

    }


    public void showOptions() {
        System.out.println("");
        System.out.println("-------MENU MACCHINARIO-------");
        System.out.println("");
        System.out.println("1) Inserisci macchinario");
        System.out.println("2) Modifica macchinario");
        System.out.println("3) Elimina macchinario");
        System.out.println("4) Visualizza macchinari");
        System.out.println("5) Indietro");
        this.choice = Integer.parseInt(getInput());
    }

    public void submit() {
        if (choice < 1 || choice > 5)
            MainDispatcher.getInstance().callAction("MachineManagement", "doControl", null);
        else if (choice == 5)
            MainDispatcher.getInstance().callAction("Home", "doControl", null);
        else {
            Request request = new Request();
            request.put("choice", choice);
            MainDispatcher.getInstance().callAction("Machine", "doControl", request);
        }
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}

