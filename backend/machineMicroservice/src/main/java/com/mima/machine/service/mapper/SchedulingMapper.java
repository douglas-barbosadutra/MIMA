package com.mima.machine.service.mapper;

import com.mima.machine.domain.*;
import com.mima.machine.service.dto.SchedulingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Scheduling and its DTO SchedulingDTO.
 */
@Mapper(componentModel = "spring", uses = {MachineMapper.class})
public interface SchedulingMapper extends EntityMapper<SchedulingDTO, Scheduling> {

    @Mapping(source = "machine.id", target = "machineId")
    SchedulingDTO toDto(Scheduling scheduling);

    @Mapping(target = "taskScheduleds", ignore = true)
    @Mapping(source = "machineId", target = "machine")
    Scheduling toEntity(SchedulingDTO schedulingDTO);

    default Scheduling fromId(Long id) {
        if (id == null) {
            return null;
        }
        Scheduling scheduling = new Scheduling();
        scheduling.setId(id);
        return scheduling;
    }
}
