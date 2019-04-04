package com.mima.wbs.repository;

import com.mima.wbs.domain.WBS;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WBS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WBSRepository extends JpaRepository<WBS, Long> {
	public List<WBS> findAllByIdUser(Integer idUser);
}
