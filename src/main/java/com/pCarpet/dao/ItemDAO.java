package com.pCarpet.dao;

import org.springframework.data.repository.CrudRepository;
import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;
import com.pCarpet.model.WBS;

import java.util.List;

public interface ItemDAO extends CrudRepository<Item, Integer>{
	public List<Item> findAllByWBS(WBS wbs);
	public List<Item> findAllByManufacturing(Manufacturing m);
	public Item findByItem(Item i);
	public Item findByManufacturing(Manufacturing m);
}