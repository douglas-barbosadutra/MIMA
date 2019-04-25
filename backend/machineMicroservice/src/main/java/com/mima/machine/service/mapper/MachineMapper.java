package com.mima.machine.service.mapper;

import com.mima.machine.domain.*;
import com.mima.machine.service.dto.MachineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Machine and its DTO MachineDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MachineMapper extends EntityMapper<MachineDTO, Machine> {


    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "schedulings", ignore = true)
    Machine toEntity(MachineDTO machineDTO);

    default Machine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Machine machine = new Machine();
        machine.setId(id);
        return machine;
    }
}
