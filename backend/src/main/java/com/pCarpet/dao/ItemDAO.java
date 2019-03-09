package com.pCarpet.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;
import com.pCarpet.model.WBS;

import java.util.List;

import javax.transaction.Transactional;

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

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO items (name,id_father,id_wbs,level) VALUES (:name,:id_father,:id_wbs,:level)", nativeQuery=true)
	public void insertItem(@Param("name") String name, @Param("id_father") Integer id_father, @Param("id_wbs") Integer id_wbs, @Param("level") Integer level);
	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM items WHERE id_item = :id_item", nativeQuery=true)
	public void deleteItem(@Param("id_item") Integer idItem);
}