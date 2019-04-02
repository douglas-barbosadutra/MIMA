package com.mima.wbs.repository;

import com.mima.wbs.domain.Manufacturing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Manufacturing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManufacturingRepository extends JpaRepository<Manufacturing, Long> {

}
