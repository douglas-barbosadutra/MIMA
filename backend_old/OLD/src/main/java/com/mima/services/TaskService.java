package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.TaskConverter;
import com.mima.dao.TaskDAO;
import com.mima.dto.TaskDTO;
import com.mima.model.Machine;
import com.mima.model.Task;


@Service
public class TaskService {
	
	@Autowired
	private TaskDAO taskDAO;
	
	public TaskService() {	}
	
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
