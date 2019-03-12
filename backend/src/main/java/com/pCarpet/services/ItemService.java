package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.ItemConverter;
import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.ItemDAO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.model.Item;


@Service
public class ItemService {
	
	private ItemDAO itemDAO;
	
	@Autowired
	public ItemService(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public ItemDTO getItemById(int id) {
		return (ItemConverter.convertToDto(itemDAO.findItemById(id)));
	}
	
	public ItemDTO insertItem(ItemDTO itemDTO) {
		Item item = ItemConverter.convertToEntity(itemDTO);
		itemDAO.insertItem(itemDTO.getName(), itemDTO.getIdFather(), itemDTO.getIdWBS(), itemDTO.getLevel());
		return ItemConverter.convertToDto(item); //err
	}
	
	public boolean deleteItem(int id) {
		itemDAO.deleteItem(id);
		return true;
	}
	
	public ItemDTO getItemByWBS(WBSDTO wbs){
		List<ItemDTO> list = (ItemConverter.toListDTO(itemDAO.findAllByWbs(WBSConverter.convertToEntity(wbs))));
		for(ItemDTO item: list) {
			if(item.getIdFather() == 0)
				return item;
		}
		return null;
	}	
}
