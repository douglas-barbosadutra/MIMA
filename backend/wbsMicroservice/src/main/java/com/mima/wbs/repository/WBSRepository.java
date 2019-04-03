package com.mima.wbs.repository;

import com.mima.wbs.domain.WBS;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WBS entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WBSRepository extends JpaRepository<WBS, Long> {

}
