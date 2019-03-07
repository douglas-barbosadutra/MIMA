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
	
	public void insertItem(ItemDTO itemDTO) {
		itemDAO.save(ItemConverter.convertToEntity(itemDTO));
	}
	
	public void deleteItem(int id) {
		itemDAO.deleteById(id);
	}
	
	public List<ItemDTO> getItemByWBS(WBSDTO wbs){
		return (ItemConverter.toListDTO(itemDAO.findAllByWbs(WBSConverter.convertToEntity(wbs))));
	}
	
	public List<ItemDTO> getItemByLevelAndWbs(Integer level, WBSDTO wbs){
		
		List<Item> item = itemDAO.findAllByLevelAndWbs(level,WBSConverter.convertToEntity(wbs));
		List<ItemDTO> itemDTO = ItemConverter.toListDTO(item);
		
		return itemDTO;
	}
	
	public int getMaxLevelByWbs(WBSDTO wbs) {
		
		return itemDAO.findMaxLevelByWbs(wbs.getId());
	}
	
}
