package com.mima.wbs.service.mapper;

import com.mima.wbs.domain.*;
import com.mima.wbs.service.dto.WBSDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WBS and its DTO WBSDTO.
 */
@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface WBSMapper extends EntityMapper<WBSDTO, WBS> {

    @Mapping(source = "item.id", target = "itemId")
    WBSDTO toDto(WBS wBS);

    @Mapping(source = "itemId", target = "item")
    WBS toEntity(WBSDTO wBSDTO);

    default WBS fromId(Long id) {
        if (id == null) {
            return null;
        }
        WBS wBS = new WBS();
        wBS.setId(id);
        return wBS;
    }
}
