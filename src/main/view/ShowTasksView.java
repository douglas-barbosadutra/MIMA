package main.view;

import java.util.List;

import dto.TaskDTO;
import main.MainDispatcher;
import main.controller.Request;

public class ShowTasksView implements View{
	private List<TaskDTO> tasks;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		tasks = (List<TaskDTO>) request.get("tasks");
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---LISTA TASKS---");
		for(TaskDTO t : tasks)
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
