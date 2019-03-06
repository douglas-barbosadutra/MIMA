package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.TaskConverter;
import com.pCarpet.dao.TaskDAO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Machine;


@Service
public class TaskService {
	
	private TaskDAO taskDAO;
	
	@Autowired
	public TaskService(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	
	public void deleteTask(int id) {
		this.taskDAO.deleteById(id);
	}
	
	public void insertTask(TaskDTO taskDTO) {
		this.taskDAO.save(TaskConverter.convertToEntity(taskDTO));
	}
	
	public List<TaskDTO> getAllTasks(int idMacchinario){
		Machine m = new Machine();
		m.setId(idMacchinario);
		return(TaskConverter.toListDTO(this.taskDAO.findAllByMachine(m)));
	}
	
	public void updateTask(TaskDTO taskDTO) {
		this.taskDAO.save(TaskConverter.convertToEntity(taskDTO));
	}
}
