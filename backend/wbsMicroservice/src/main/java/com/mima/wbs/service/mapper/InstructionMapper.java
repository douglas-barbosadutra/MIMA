package com.mima.wbs.service.mapper;

import com.mima.wbs.domain.*;
import com.mima.wbs.service.dto.InstructionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Instruction and its DTO InstructionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstructionMapper extends EntityMapper<InstructionDTO, Instruction> {



    default Instruction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Instruction instruction = new Instruction();
        instruction.setId(id);
        return instruction;
    }
}
