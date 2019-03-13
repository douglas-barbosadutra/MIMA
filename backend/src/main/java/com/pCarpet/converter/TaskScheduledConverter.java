package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.model.Scheduling;
import com.pCarpet.model.Task;
import com.pCarpet.model.TaskScheduled;

public class TaskScheduledConverter {

	public static TaskScheduled convertToEntity(TaskScheduledDTO taskScheduledDto) {
		TaskScheduled task = null;
		if (taskScheduledDto != null) {
			task = new TaskScheduled();
			if(taskScheduledDto.getId() != 0)
				task.setId(taskScheduledDto.getId());

			Task temp = new Task();
			temp.setId(taskScheduledDto.getIdTask());
			task.setTask(temp);
			
			Scheduling s = new Scheduling();
			s.setId(taskScheduledDto.getIdScheduling());
			task.setScheduling(s);
		}
		return task;
	}

	public static TaskScheduledDTO convertToDto(TaskScheduled task) {
		TaskScheduledDTO taskScheduledDto = null;
		if (task != null) {
			taskScheduledDto = new TaskScheduledDTO();
			taskScheduledDto.setId(task.getId());
			taskScheduledDto.setIdTask(task.getTask().getId());
			taskScheduledDto.setHasFather(!task.getFatherList().isEmpty());			//se la lista dei padri dell'entita` taskscheduled è piena(ha dei padri) setto l'attributo has father a true
			taskScheduledDto.setName(task.getTask().getDescription());
			taskScheduledDto.setIdScheduling(task.getScheduling().getId());
			taskScheduledDto.setTaskScheduledChildren(TaskScheduledConverter.toListDTO(task.getChildsList()));
		}
		return taskScheduledDto;
	}

	public static List<TaskScheduledDTO> toListDTO(List<TaskScheduled> list) {
		List<TaskScheduledDTO> listTaskScheduledDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (TaskScheduled task : list) {
				listTaskScheduledDTO.add(TaskScheduledConverter.convertToDto(task));
			}
		}
		return listTaskScheduledDTO;
	}
}
