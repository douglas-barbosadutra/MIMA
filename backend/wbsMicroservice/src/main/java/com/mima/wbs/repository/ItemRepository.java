package com.mima.wbs.repository;

import com.mima.wbs.domain.Item;
import com.mima.wbs.domain.WBS;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Item entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	public List<Item> findAllByWbs(WBS wbs);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO item (name, father_id, wbs_id) VALUES (:name, :id_father, :id_wbs)", nativeQuery = true)
	public void insertItem(@Param("name") String name, @Param("id_father") Long long1,
			@Param("id_wbs") Long long2);
}
