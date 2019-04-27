package com.mima.machine.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.machine.domain.TaskScheduledRelation;
import com.mima.machine.service.dto.TaskScheduledRelationDTO;

public class TaskScheduledRelationConverter {

	public static TaskScheduledRelationDTO toDto(TaskScheduledRelation taskScheduledRelation) {
		if(taskScheduledRelation == null) {
			return null;
		}
		TaskScheduledRelationDTO taskScheduledRelationDTO = new TaskScheduledRelationDTO();
		taskScheduledRelationDTO.setTaskScheduledFirstId(taskScheduledRelation.getTaskScheduledFirst().getId());
		taskScheduledRelationDTO.setTaskScheduledSecondId(taskScheduledRelation.getTaskScheduledSecond().getId());
		return taskScheduledRelationDTO;
	}
	
	public static List<TaskScheduledRelationDTO> toDtoList(List<TaskScheduledRelation> list){
		List<TaskScheduledRelationDTO> result = new ArrayList<TaskScheduledRelationDTO>();
		for(TaskScheduledRelation taskScheduledRelation : list) {
			result.add(toDto(taskScheduledRelation));
		}
		return result;
	}
}
