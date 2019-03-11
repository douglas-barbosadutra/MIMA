package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.TaskConverter;
import com.pCarpet.dao.TaskDAO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.Task;


@Service
public class TaskService {
	
	private TaskDAO taskDAO;
	
	@Autowired
	public TaskService(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	
	public boolean deleteTask(int id) {
		this.taskDAO.deleteById(id);
		return true;
	}
	
	public TaskDTO insertTask(TaskDTO taskDTO) {
		Task task = TaskConverter.convertToEntity(taskDTO);
		this.taskDAO.saveAndFlush(task);
		return TaskConverter.convertToDto(task);
	}
	
	public List<TaskDTO> getAllTasks(int idMacchinario){
		Machine m = new Machine();
		m.setId(idMacchinario);
		return(TaskConverter.toListDTO(this.taskDAO.findAllByMachine(m)));
	}
}
