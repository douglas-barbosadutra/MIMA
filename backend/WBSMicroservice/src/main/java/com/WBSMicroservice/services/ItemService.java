package com.WBSMicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WBSMicroservice.model.Item;
import com.WBSMicroservice.converter.ItemConverter;
import com.WBSMicroservice.converter.WBSConverter;
import com.WBSMicroservice.dao.ItemDAO;
import com.WBSMicroservice.dto.ItemDTO;
import com.WBSMicroservice.dto.WBSDTO;


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
