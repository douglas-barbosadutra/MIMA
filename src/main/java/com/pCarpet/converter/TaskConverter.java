package com.pCarpet.converter;

import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Task;

public class TaskConverter{

	public static Task convertToEntity(TaskDTO taskdto) {
		Task task = null;
		if(taskdto != null) {
			task = new Task();
			task.setId(taskdto.getId());
			task.setDescription(taskdto.getDescrizione());
		}
		return task;
	}

	public static TaskDTO convertToDto(Task task) {
		TaskDTO taskdto = null;
		if(task != null) {
			taskdto = new TaskDTO();
			taskdto.setId(task.getId());
			taskdto.setDescrizione(task.getDescription());
		}
		return taskdto;
	}
}

