package com.mima.wbs.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.wbs.domain.Item;
import com.mima.wbs.domain.WBS;
import com.mima.wbs.service.dto.ItemDTO;

public class ItemConverter {
	
	public static ItemDTO convertToDto(Item item) {
		ItemDTO itemDTO = null;
		if(item != null) {
			itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setName(item.getName());
			Long id1 = itemFatherId( item );
	        if ( id1 != null ) {
	            itemDTO.setFatherId( id1 );
	        }
	        Long id = itemWbsId( item );
	        if ( id != null ) {
	            itemDTO.setWbsId( id );
	        }
			itemDTO.setItems(ItemConverter.toDto(new ArrayList<Item>(item.getItems())));
		}
		return itemDTO;
	}
	
	public static List<ItemDTO> toDto(List<Item> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ItemDTO> list = new ArrayList<ItemDTO>( entityList.size() );
        for ( Item item : entityList ) {
            list.add( convertToDto( item ) );
        }

        return list;
    }
	
	 private static Long itemWbsId(Item item) {
	        if ( item == null ) {
	            return null;
	        }
	        WBS wbs = item.getWbs();
	        if ( wbs == null ) {
	            return null;
	        }
	        Long id = wbs.getId();
	        if ( id == null ) {
	            return null;
	        }
	        return id;
	    }

	    private static Long itemFatherId(Item item) {
	        if ( item == null ) {
	            return null;
	        }
	        Item father = item.getFather();
	        if ( father == null ) {
	            return null;
	        }
	        Long id = father.getId();
	        if ( id == null ) {
	            return null;
	        }
	        return id;
	    }
}
