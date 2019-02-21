package main.service;

import java.util.ArrayList;

import main.dao.TaskDAO;
import main.model.Task;

public class TaskService {
	
	private TaskDAO taskDAO;
	
	public TaskService() {
		this.taskDAO = new TaskDAO();
	}
	
	public void deleteTask(int id) {
		this.taskDAO.deleteTask(id);
	}
	
	public boolean insertTask(String descrizione, int macchinario) {
		return this.taskDAO.insertTask(descrizione,macchinario);
	}
	
	public ArrayList<Task> getAllTasks(int macchinario){
		return this.taskDAO.getAllTasks(macchinario);
	}
	
	public void updateTask(String descrizione, int id) {
		this.taskDAO.updateTask(descrizione, id);
	}
}
