package com.WBSMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WBSMicroservice.model.Item;
import com.WBSMicroservice.model.WBS;
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
	
	public ItemDTO getItemByWBS(int idWBS){
		try {
		WBSDTO WBS = new WBSDTO();
		WBS.setId(idWBS);
		WBS ent	 = WBSConverter.convertToEntity(WBS);
		System.out.println(ent);
		List<Item> temp = itemDAO.findAllByWbs(ent);
		System.out.println(temp);
		List<ItemDTO> list = (ItemConverter.toListDTO(temp));
		for(ItemDTO item: list) {
			if(item.getIdFather() == 0)
				return item;
		}
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		
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
