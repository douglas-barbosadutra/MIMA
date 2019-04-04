package com.mima.machine.service.mapper;

import com.mima.machine.domain.*;
import com.mima.machine.service.dto.TaskScheduledDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TaskScheduled and its DTO TaskScheduledDTO.
 */
@Mapper(componentModel = "spring", uses = {SchedulingMapper.class, TaskMapper.class})
public interface TaskScheduledMapper extends EntityMapper<TaskScheduledDTO, TaskScheduled> {

    @Mapping(source = "scheduling.id", target = "schedulingId")
    @Mapping(source = "task.id", target = "taskId")
    TaskScheduledDTO toDto(TaskScheduled taskScheduled);

    @Mapping(source = "schedulingId", target = "scheduling")
    @Mapping(source = "taskId", target = "task")
    @Mapping(target = "children", ignore = true)
    TaskScheduled toEntity(TaskScheduledDTO taskScheduledDTO);

    default TaskScheduled fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaskScheduled taskScheduled = new TaskScheduled();
        taskScheduled.setId(id);
        return taskScheduled;
    }
}
