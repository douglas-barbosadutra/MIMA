package com.mima.machine.service.mapper;

import com.mima.machine.domain.*;
import com.mima.machine.service.dto.InputDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Input and its DTO InputDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskScheduledMapper.class})
public interface InputMapper extends EntityMapper<InputDTO, Input> {

    @Mapping(source = "taskScheduled.id", target = "taskScheduledId")
    InputDTO toDto(Input input);

    @Mapping(source = "taskScheduledId", target = "taskScheduled")
    Input toEntity(InputDTO inputDTO);

    default Input fromId(Long id) {
        if (id == null) {
            return null;
        }
        Input input = new Input();
        input.setId(id);
        return input;
    }
}
