package main.converter;

import main.dto.*;
import main.model.Task;

public class TaskConverter{

	public static Task convertToUser(TaskDTO taskdto) {
		return (new Task(taskdto.getID(), taskdto.getDescrizione(), taskdto.getMacchinario(), taskdto.getTimestamp()));
	}

	public static TaskDTO convertToDto(Task task) {
		return (new TaskDTO(task.getID(), task.getDescrizione(), task.getMacchinario(), task.getTimestamp()));
	}
}

