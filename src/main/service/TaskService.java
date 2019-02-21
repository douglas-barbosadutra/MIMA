package main.service;

import java.util.ArrayList;

import main.dao.TaskDAO;
import main.model.Task;

public class TaskService {
	
	private TaskDAO taskDAO;
	
	public TaskService() {
		this.taskDAO = new TaskDAO();
	}
	
	public boolean insertTask(Task task) {
		return this.taskDAO.insertTask(task);
	}
	
	public ArrayList<Task> getAllTasks(int macchinario){
		return this.taskDAO.getAllTasks(macchinario);
	}
}
