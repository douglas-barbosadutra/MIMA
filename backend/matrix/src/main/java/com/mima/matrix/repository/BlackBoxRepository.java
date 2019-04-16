package com.mima.matrix.repository;

import com.mima.matrix.domain.BlackBox;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BlackBox entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlackBoxRepository extends JpaRepository<BlackBox, Long> {

}
