package main.view;

import java.util.List;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Task;

public class ShowTasksView implements View{
	private List<Task> tasks;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		tasks = (List<Task>) request.get("tasks");
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---LISTA TASKS---");
		for(Task t : tasks)
			System.out.println(t);
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		MainDispatcher.getInstance().callAction("Task", "doControl", null);
	}

}
