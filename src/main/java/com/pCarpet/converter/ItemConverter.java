package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.ItemDTO;
import com.pCarpet.model.Item;
import com.pCarpet.model.WBS;

public class ItemConverter {
	
	public static ItemDTO convertToDto(Item item) {
		ItemDTO itemDTO = null;
		if(item != null) {
			itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setName(item.getName());
			
			if(item.getFather() != null) {
				Item father = item.getFather();
				ItemDTO fatherDTO = ItemConverter.convertToDto(father);
				itemDTO.setFather(fatherDTO);	
			}
			
			itemDTO.setLevel(item.getLevel());
			itemDTO.setItemChildrenDTO(ItemConverter.toListDTO(item.getChildsList()));
			itemDTO.setIdWBS(item.getWbs().getId());
		}
		return itemDTO;
	}
	
	public static Item convertToEntity(ItemDTO itemDTO) {
		Item item = null;
		if(itemDTO != null) {
			item = new Item();
			item.setId(itemDTO.getId());
			item.setName(itemDTO.getName());
			
			if(itemDTO.getFather() != null) {
				
				ItemDTO fatherDTO = itemDTO.getFather();
				Item father = ItemConverter.convertToEntity(fatherDTO);
				item.setFather(father);
			}
			
			item.setLevel(itemDTO.getLevel());
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
