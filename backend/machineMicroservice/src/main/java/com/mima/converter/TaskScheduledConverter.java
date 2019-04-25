package com.mima.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.machine.domain.Scheduling;
import com.mima.machine.domain.Task;
import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.service.dto.TaskScheduledDTO;
import com.mima.machine.service.dto.TaskScheduledRelationDTO;

public class TaskScheduledConverter {

	public static TaskScheduled toEntity(TaskScheduledDTO taskScheduledDto) {
		TaskScheduled task = null;

		return task;
	}

	public static TaskScheduledDTO toDto(TaskScheduled taskScheduled) {
		if (taskScheduled == null) {
			return null;
		}

		TaskScheduledDTO taskScheduledDTO = new TaskScheduledDTO();

		Long id = taskScheduledSchedulingId(taskScheduled);
		if (id != null) {
			taskScheduledDTO.setSchedulingId(id);
		}
		Long id1 = taskScheduledTaskId(taskScheduled);
		if (id1 != null) {
			taskScheduledDTO.setTaskId(id1);
		}
		taskScheduledDTO.setId(taskScheduled.getId());
		taskScheduledDTO.setName(taskScheduled.getName());
		taskScheduledDTO.setIdOutput(taskScheduled.getIdOutput());
		List<TaskScheduledRelationDTO> list = TaskScheduledRelationConverter.toDtoList(new ArrayList<>(taskScheduled.getTaskScheduledRelations()));
		list.addAll(TaskScheduledRelationConverter.toDtoList(new ArrayList<>(taskScheduled.getTaskScheduledRelationTwos())));
		taskScheduledDTO.setTaskScheduledRelationList(list);
		return taskScheduledDTO;
	}

	private static Long taskScheduledSchedulingId(TaskScheduled taskScheduled) {
		if (taskScheduled == null) {
			return null;
		}
		Scheduling scheduling = taskScheduled.getScheduling();
		if (scheduling == null) {
			return null;
		}
		Long id = scheduling.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

	private static Long taskScheduledTaskId(TaskScheduled taskScheduled) {
		if (taskScheduled == null) {
			return null;
		}
		Task task = taskScheduled.getTask();
		if (task == null) {
			return null;
		}
		Long id = task.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

	public static List<TaskScheduledDTO> toListDTO(List<TaskScheduled> list) {
		List<TaskScheduledDTO> listTaskScheduledDTO = new ArrayList<TaskScheduledDTO>();
		if (!list.isEmpty()) {
			for (TaskScheduled task : list) {
				listTaskScheduledDTO.add(TaskScheduledConverter.toDto(task));
			}
		}
		return listTaskScheduledDTO;
	}
}
