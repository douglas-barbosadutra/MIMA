package com.mima.machine.service.mapper;

import com.mima.machine.domain.*;
import com.mima.machine.service.dto.TaskScheduledRelationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TaskScheduledRelation and its DTO TaskScheduledRelationDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskScheduledMapper.class})
public interface TaskScheduledRelationMapper extends EntityMapper<TaskScheduledRelationDTO, TaskScheduledRelation> {

    @Mapping(source = "taskScheduledFirst.id", target = "taskScheduledFirstId")
    @Mapping(source = "taskScheduledSecond.id", target = "taskScheduledSecondId")
    TaskScheduledRelationDTO toDto(TaskScheduledRelation taskScheduledRelation);

    @Mapping(source = "taskScheduledFirstId", target = "taskScheduledFirst")
    @Mapping(source = "taskScheduledSecondId", target = "taskScheduledSecond")
    TaskScheduledRelation toEntity(TaskScheduledRelationDTO taskScheduledRelationDTO);

    default TaskScheduledRelation fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaskScheduledRelation taskScheduledRelation = new TaskScheduledRelation();
        taskScheduledRelation.setId(id);
        return taskScheduledRelation;
    }
}
