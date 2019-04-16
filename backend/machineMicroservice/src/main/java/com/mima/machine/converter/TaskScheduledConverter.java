package com.mima.machine.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mima.machine.domain.Scheduling;
import com.mima.machine.domain.Task;
import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.service.dto.TaskScheduledDTO;

public class TaskScheduledConverter {

	public static TaskScheduled toEntity(TaskScheduledDTO taskScheduledDto) {
		TaskScheduled task = null;
		
		return task;
	}
	
	public static TaskScheduledDTO toDto(TaskScheduled taskScheduled) {
		if ( taskScheduled == null ) {
            return null;
        }

        TaskScheduledDTO taskScheduledDTO = new TaskScheduledDTO();

        Long id = taskScheduledSchedulingId( taskScheduled );
        if ( id != null ) {
            taskScheduledDTO.setSchedulingId( id );
        }
        Long id1 = taskScheduledTaskId( taskScheduled );
        if ( id1 != null ) {
            taskScheduledDTO.setTaskId( id1 );
        }
        taskScheduledDTO.setId( taskScheduled.getId() );
        taskScheduledDTO.setName( taskScheduled.getName() );
        taskScheduledDTO.setIdOutput( taskScheduled.getIdOutput() );
        taskScheduledDTO.setTaskScheduledList(TaskScheduledConverter.toListDTO(new ArrayList<TaskScheduled>(taskScheduled.getChildren())));
        
        return taskScheduledDTO;
	}
	
	private static Long taskScheduledSchedulingId(TaskScheduled taskScheduled) {
        if ( taskScheduled == null ) {
            return null;
        }
        Scheduling scheduling = taskScheduled.getScheduling();
        if ( scheduling == null ) {
            return null;
        }
        Long id = scheduling.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private static Long taskScheduledTaskId(TaskScheduled taskScheduled) {
        if ( taskScheduled == null ) {
            return null;
        }
        Task task = taskScheduled.getTask();
        if ( task == null ) {
            return null;
        }
        Long id = task.getId();
        if ( id == null ) {
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
