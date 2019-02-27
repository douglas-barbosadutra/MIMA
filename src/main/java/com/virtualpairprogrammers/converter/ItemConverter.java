package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.Item;
import com.virtualpairprogrammers.dto.ItemDTO;

public class ItemConverter {
	
	public static Item convertToItem(ItemDTO itemdto) {
		return (new Item(itemdto.getID(), itemdto.getDescrizione()));
	}

	public static ItemDTO convertToDto(Item item) {
		return (new ItemDTO(item.getID(), item.getDescrizione()));
	}
	
}
