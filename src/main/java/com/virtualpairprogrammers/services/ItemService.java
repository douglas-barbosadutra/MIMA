package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.ItemConverter;
import com.virtualpairprogrammers.dao.ItemDAO;
import com.virtualpairprogrammers.domain.Item;
import com.virtualpairprogrammers.dto.ItemDTO;

public class ItemService {
	
	private ItemDAO itemDAO;
	
	public ItemService() {
		this.itemDAO = new ItemDAO();
	}
	
	public ItemDTO getItemById(int id) {
		Item item = itemDAO.getItemById(id);
		return (ItemConverter.convertToDto(item));
	}
	
}
