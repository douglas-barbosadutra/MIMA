package com.pCarpet.services;

import java.util.ArrayList;
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
		int idChild = osDTO.getIdChild();
		if(osDTO.getIdTask() != 0) {
			TaskScheduledDTO taskScheduled = new TaskScheduledDTO();
			taskScheduled.setIdTask(osDTO.getIdTask());
			taskScheduled.setIdScheduling(osDTO.getIdScheduling());
			taskScheduled = insertTaskScheduled(taskScheduled);
			idChild = taskScheduled.getId();
		}
		taskScheduledDAO.insertScheduledRelations(idChild, osDTO.getIdFather());
	}
	
	public TaskScheduledDTO insertTaskScheduled(TaskScheduledDTO taskScheduled) {
		TaskScheduled task = TaskScheduledConverter.convertToEntity(taskScheduled);
		task = taskScheduledDAO.save(task);
		taskScheduledDAO.flush();
		return TaskScheduledConverter.convertToDto(task);
	}
	
	public boolean deleteTaskScheduled(int id) {
		taskScheduledDAO.deleteById(id);
		return true;
	}
	
	public List<TaskScheduledDTO> getTaskScheduling(int idScheduling) {
		SchedulingDTO schedulingDTO = new SchedulingDTO();
		schedulingDTO.setId(idScheduling);
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
	
	public List<OperationSchedulingDTO> getOperationScheduling(int idScheduling){
		List<TaskScheduledDTO> list = this.getTaskScheduling(idScheduling);
		List<OperationSchedulingDTO> result = new ArrayList<>();
		for(TaskScheduledDTO task: list) {
			if(task.getTaskScheduledChildren() != null) {
				for(TaskScheduledDTO child: task.getTaskScheduledChildren()) {
					OperationSchedulingDTO temp = new OperationSchedulingDTO(task.getId(), child.getId(), 0, 0);			
					result.add(temp);
				}
			}
		}
		return result;
	}
	
	public boolean insertOutput(int idItem, int idOperationScheduling) {
		taskScheduledDAO.insertOutput(idItem, idOperationScheduling);
		return true;
	}
	
}
