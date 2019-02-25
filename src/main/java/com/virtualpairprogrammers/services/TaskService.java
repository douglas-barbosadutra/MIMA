package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.TaskConverter;
import com.virtualpairprogrammers.dao.TaskDAO;
import com.virtualpairprogrammers.domain.Task;
import com.virtualpairprogrammers.dto.TaskDTO;

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
	
	public List<TaskDTO> getAllTasks(int macchinario){
		
    	List<Task> tasks = this.taskDAO.getAllTasks(macchinario);;
    	List<TaskDTO> taskdto = new ArrayList<>();
    	
		for(Task task: tasks) {
			taskdto.add(TaskConverter.convertToDto(task));
		}
		return taskdto;
		
	}
	
	public void updateTask(String descrizione, int id) {
		this.taskDAO.updateTask(descrizione, id);
	}
	
}
