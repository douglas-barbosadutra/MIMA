package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.Task;
import com.virtualpairprogrammers.dto.TaskDTO;

public class TaskConverter{

	public static Task convertToUser(TaskDTO taskdto) {
		return (new Task(taskdto.getID(), taskdto.getDescrizione(), taskdto.getMacchinario()));
	}

	public static TaskDTO convertToDto(Task task) {
		return (new TaskDTO(task.getID(), task.getDescrizione(), task.getMacchinario()));
	}
}

