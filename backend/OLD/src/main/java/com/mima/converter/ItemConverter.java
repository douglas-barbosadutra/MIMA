package com.mima.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.dto.ItemDTO;
import com.mima.model.Item;
import com.mima.model.WBS;

public class ItemConverter {
	
	public static ItemDTO convertToDto(Item item) {
		ItemDTO itemDTO = null;
		if(item != null) {
			itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setName(item.getName());
			if(item.getFather() == null) {
				itemDTO.setIdFather(0);
			}else {
				itemDTO.setIdFather(item.getFather().getId());
			}
			itemDTO.setIdWBS(item.getWbs().getId());
			itemDTO.setItemChildrenDTO(ItemConverter.toListDTO(item.getChildsList()));
		}
		return itemDTO;
	}
	
	public static Item convertToEntity(ItemDTO itemDTO) {
		Item item = null;
		if(itemDTO != null) {
			item = new Item();
			item.setId(itemDTO.getId());
			item.setName(itemDTO.getName());
			item.setFather(null);
			if(itemDTO.getIdFather() != 0) {
				Item father = new Item();
				father.setId(itemDTO.getIdFather());
				item.setFather(father);
			}			
			WBS wbs = new WBS();
			wbs.setId(itemDTO.getIdWBS());
			item.setWbs(wbs);
		}
		return item;
	}
	
	public static List<ItemDTO> toListDTO(List<Item> list){
		List<ItemDTO> listItemDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Item item : list) {
				listItemDTO.add(ItemConverter.convertToDto(item));
			}
		}
		return listItemDTO;
	}
	
	public static List<Item> toListEntity(List<ItemDTO> list){
		List<Item> listItem = new ArrayList<>();
		if (!list.isEmpty()) {
			for(ItemDTO itemDTO : list) {
				listItem.add(ItemConverter.convertToEntity(itemDTO));
			}
		}
		return listItem;
	}
}
