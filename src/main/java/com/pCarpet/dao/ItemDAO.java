package com.pCarpet.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;
import com.pCarpet.model.WBS;

import java.util.List;

public interface ItemDAO extends CrudRepository<Item, Integer>{
	
	final String FIND_MAX_LEVEL_BY_WBS = "select max(level) from items where id_wbs = :wbs";
	
	public List<Item> findAllByWbs(WBS wbs);
	public List<Item> findAllByManufacturings(Manufacturing manufacturings);
	public List<Item> findAllByLevelAndWbs(Integer level, WBS wbs);
	
	public Item findByFather(Item i);
	public Item findByManufacturings(Manufacturing m);
	public Item findItemById(int id);
	
	@Query(value = "select max(level) from items where id_wbs = :wbs", nativeQuery=true)
	public int findMaxLevelByWbs(@Param("wbs") int wbs);
	
}