package main.view;

import main.MainDispatcher;
import main.controller.Request;

import java.util.Scanner;

public class HomeUserView implements View {

    private int choice;

    public void showResults(Request request) {

    }


    public void showOptions() {
        System.out.println("");
        System.out.println("-------MENU UTENTE-------");
        System.out.println("");
        System.out.println("1) Gestione macchinari");
        System.out.println("2) Gestione task");
        System.out.println("3) Logout");
        this.choice = Integer.parseInt(getInput());
    }

    public void submit() {
        if (choice == 1)
            MainDispatcher.getInstance().callAction("MachineManagement", "doControl", null);
        else if (choice == 2)
            MainDispatcher.getInstance().callAction("TaskManagement", "doControl", null);
        else
            MainDispatcher.getInstance().callAction("Login", "doControl", null);
    }


    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}
