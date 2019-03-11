package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.Task;

public class TaskConverter{

	public static Task convertToEntity(TaskDTO taskdto) {
		Task task = null;
		if(taskdto != null) {
			task = new Task();
			task.setId(taskdto.getId());
			task.setDescription(taskdto.getDescription());
			Machine machine = new Machine();
			machine.setId(taskdto.getIdMachine());
			task.setMachine(machine);
		}
		return task;
	}

	public static TaskDTO convertToDto(Task task) {
		TaskDTO taskdto = null;
		if(task != null) {
			taskdto = new TaskDTO();
			taskdto.setId(task.getId());
			taskdto.setDescription(task.getDescription());
			taskdto.setIdMachine(task.getMachine().getId());
		}
		return taskdto;
	}
	
	public static List<TaskDTO> toListDTO(List<Task> list){
		List<TaskDTO> listTaskDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Task task : list) {
				listTaskDTO.add(TaskConverter.convertToDto(task));
			}
		}
		return listTaskDTO;
	}
}

