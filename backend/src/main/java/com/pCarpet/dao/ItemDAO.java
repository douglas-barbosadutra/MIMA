package com.pCarpet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;
import com.pCarpet.model.WBS;

import java.util.List;

import javax.transaction.Transactional;

public interface ItemDAO extends JpaRepository<Item, Integer>{
	
	final String FIND_MAX_LEVEL_BY_WBS = "select max(level) from items where id_wbs = :wbs";
	
	public List<Item> findAllByWbs(WBS wbs);
	public List<Item> findAllByManufacturings(Manufacturing manufacturings);
	public List<Item> findAll();
	
	public Item findByFather(Item i);
	public Item findByManufacturings(Manufacturing m);
	public Item findItemById(int id);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO items (name,id_father,id_wbs) VALUES (:name,:id_father,:id_wbs)", nativeQuery=true)
	public void insertItem(@Param("name") String name, @Param("id_father") Integer id_father, @Param("id_wbs") Integer id_wbs);
	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM items WHERE id_item = :id_item", nativeQuery=true)
	public void deleteItem(@Param("id_item") Integer idItem);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO inputs (id_item, id_task) VALUES (:id_item, :id_task)", nativeQuery=true)
	public void insertInput(@Param("id_item") Integer id_item, @Param("id_task") Integer id_task);
}