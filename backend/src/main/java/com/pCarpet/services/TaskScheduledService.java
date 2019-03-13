package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.SchedulingConverter;
import com.pCarpet.converter.TaskScheduledConverter;
import com.pCarpet.dao.TaskScheduledDAO;
import com.pCarpet.dto.OperationSchedulingDTO;
import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.model.TaskScheduled;

@Service
public class TaskScheduledService {

	TaskScheduledDAO taskScheduledDAO;
	TaskService taskService;

	@Autowired
	TaskScheduledService(TaskScheduledDAO taskScheduledDAO, TaskService taskService) {
		this.taskScheduledDAO = taskScheduledDAO;
		this.taskService = taskService;
	}
	
	public void insertScheduledRelations(OperationSchedulingDTO osDTO) {
		if(osDTO.getIdTask() != 0) {
			TaskScheduledDTO taskScheduled = new TaskScheduledDTO();
			taskScheduled.setIdTask(osDTO.getIdTask());
			taskScheduled.setIdScheduling(osDTO.getIdScheduling());
			insertTaskScheduled(taskScheduled);
		}
		taskScheduledDAO.insertScheduledRelations(osDTO.getIdChild(), osDTO.getIdFather());
	}
	
	public TaskScheduledDTO insertTaskScheduled(TaskScheduledDTO taskScheduled) {
		TaskScheduled task = TaskScheduledConverter.convertToEntity(taskScheduled);
		taskScheduledDAO.save(task);
		
		return TaskScheduledConverter.convertToDto(task);//errore
	}
	
	public boolean deleteTaskScheduled(int id) {
		taskScheduledDAO.deleteById(id);
		return true;
	}
	
	public List<TaskScheduledDTO> getTaskScheduling(int idScheduling) {
		SchedulingDTO schedulingDTO = new SchedulingDTO();
		return TaskScheduledConverter.toListDTO(taskScheduledDAO.findAllByScheduling(SchedulingConverter.convertToEntity(schedulingDTO)));
	}
	
	public TaskScheduledDTO getTaskScheduledRoot(int idScheduling) {
		List<TaskScheduledDTO> list = this.getTaskScheduling(idScheduling);
		for(TaskScheduledDTO i: list) {
			if(!i.isHasFather())
				return i;
		}
		return null;
	}
}
