package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.ItemConverter;
import com.mima.converter.WBSConverter;
import com.mima.dao.ItemDAO;
import com.mima.dto.ItemDTO;
import com.mima.dto.WBSDTO;
import com.mima.model.Item;


@Service
public class ItemService {
	
	@Autowired
	private ItemDAO itemDAO;
	
	public ItemService() {}
	
	public ItemDTO getItemById(int id) {
		return (ItemConverter.convertToDto(itemDAO.findItemById(id)));
	}
	
	public ItemDTO insertItem(ItemDTO itemDTO) {
		Item item = ItemConverter.convertToEntity(itemDTO);
		if(itemDTO.getIdFather() != 0)
			itemDAO.insertItem(itemDTO.getName(), itemDTO.getIdFather(), itemDTO.getIdWBS());
		else
			itemDAO.insertItem(itemDTO.getName(), null, itemDTO.getIdWBS());
		return ItemConverter.convertToDto(item); 
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
	
	public List<ItemDTO> getAllItem(){
		return ItemConverter.toListDTO(itemDAO.findAll());
	}
	
	public boolean insertInput(int idItem, int idTask) {
		itemDAO.insertInput(idItem, idTask);
		return true;
	}
}
