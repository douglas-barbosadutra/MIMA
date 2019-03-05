package com.pCarpet.converter;

import com.pCarpet.dto.ItemDTO;
import com.pCarpet.model.Item;

public class ItemConverter {
	
	public static ItemDTO convertToDto(Item item) {
		ItemDTO itemDTO = null;
		if(item != null) {
			itemDTO = new ItemDTO();
			itemDTO.setDescrizione(item.getName());
			itemDTO.setId(item.getId());
		}
		return itemDTO;
	}
	
	public static Item convertToEntity(ItemDTO itemDTO) {
		Item item = null;
		if(itemDTO != null) {
			item = new Item();
			item.setId(itemDTO.getId());
			item.setName(itemDTO.getDescrizione());
		}
		return item;
	}
	
}
