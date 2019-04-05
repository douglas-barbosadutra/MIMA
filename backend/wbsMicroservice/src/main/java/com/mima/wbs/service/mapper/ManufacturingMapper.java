package com.mima.wbs.service.mapper;

import com.mima.wbs.domain.*;
import com.mima.wbs.service.dto.ManufacturingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Manufacturing and its DTO ManufacturingDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class, InstructionMapper.class})
public interface ManufacturingMapper extends EntityMapper<ManufacturingDTO, Manufacturing> {

    @Mapping(source = "output.id", target = "outputId")
    @Mapping(source = "instruction.id", target = "instructionId")
    ManufacturingDTO toDto(Manufacturing manufacturing);

    @Mapping(source = "outputId", target = "output")
    @Mapping(source = "instructionId", target = "instruction")
    Manufacturing toEntity(ManufacturingDTO manufacturingDTO);

    default Manufacturing fromId(Long id) {
        if (id == null) {
            return null;
        }
        Manufacturing manufacturing = new Manufacturing();
        manufacturing.setId(id);
        return manufacturing;
    }
}
