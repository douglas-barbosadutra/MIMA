package com.pCarpet.dao;

import org.springframework.data.repository.CrudRepository;
import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;
import com.pCarpet.model.WBS;

import java.util.List;

public interface ItemDAO extends CrudRepository<Item, Integer>{
	public List<Item> findAllByWbs(WBS wbs);
	public List<Item> findAllByManufacturings(Manufacturing manufacturings);
	public Item findByFather(Item i);
	public Item findByManufacturings(Manufacturing m);
	public Item findItemById(int id);
}