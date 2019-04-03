package com.MachineMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MachineMicroservice.converter.TaskConverter;
import com.MachineMicroservice.dao.TaskDAO;
import com.MachineMicroservice.dto.TaskDTO;
import com.MachineMicroservice.model.Machine;
import com.MachineMicroservice.model.Task;

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
		return TaskConverter.convertToDto(taskDAO.saveAndFlush(task));
	}
	
	public List<TaskDTO> getAllTasks(int idMachine){
		Machine m = new Machine();
		m.setId(idMachine);
		return(TaskConverter.toListDTO(this.taskDAO.findAllByMachine(m)));
	}
}
