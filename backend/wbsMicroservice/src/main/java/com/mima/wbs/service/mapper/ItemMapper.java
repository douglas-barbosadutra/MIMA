package com.mima.wbs.service.mapper;

import com.mima.wbs.domain.*;
import com.mima.wbs.service.dto.ItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Item and its DTO ItemDTO.
 */
@Mapper(componentModel = "spring", uses = {WBSMapper.class})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "wbs.id", target = "wbsId")
    @Mapping(source = "father.id", target = "fatherId")
    ItemDTO toDto(Item item);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "manufacturings", ignore = true)
    @Mapping(source = "wbsId", target = "wbs")
    @Mapping(source = "fatherId", target = "father")
    Item toEntity(ItemDTO itemDTO);

    default Item fromId(Long id) {
        if (id == null) {
            return null;
        }
        Item item = new Item();
        item.setId(id);
        return item;
    }
}
