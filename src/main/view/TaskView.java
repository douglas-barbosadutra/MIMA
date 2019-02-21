package main.view;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Task;
import main.service.TaskService;

public class TaskView implements View{
	
	private TaskService taskService;
	private String mode;
	
	public TaskView() {
		this.taskService = new TaskService();
		this.mode = "all";
	}

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		this.mode = (String)request.get("mode");
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		if(mode.equals("insert")) {
			
			System.out.println("Inserisci i dati del nuovo task:");
			System.out.print("Descrizione:");
			String descrizione = getInput();
			
			System.out.print("ID macchinario (se non lo conosci inserisci 0 per tornare alla schermata principale): ");
			int macchinario = Integer.parseInt(getInput());
			
			if(macchinario != 0) {
				Task task = new Task(0,descrizione,macchinario,new Timestamp(System.currentTimeMillis()));
				taskService.insertTask(task);
			}
				
		}
		
		else {
			
			System.out.print("Inserisci l'id del macchinario (se non lo conosci inserisci 0 per tornare alla schermata principale): ");
			int macchinario = Integer.parseInt(getInput());
			
			if(macchinario != 0) {
				
				ArrayList<Task> tasks = taskService.getAllTasks(macchinario);
				System.out.println("---TASKS DEL MACCHINARIO SPECIFICATO---");
				System.out.println("");
				if(tasks.isEmpty())
					System.out.println("Non ci sono tasks per il macchinario specificato");
				else {
					
					for(Task t : tasks)
						System.out.println(t);
				}
			}
			
		}
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
		MainDispatcher.getInstance().callAction("Home", "doControl", null);
	}

}
