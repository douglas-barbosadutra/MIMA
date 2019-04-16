package com.mima.matrix.repository;

import com.mima.matrix.domain.Matrix;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Matrix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatrixRepository extends JpaRepository<Matrix, Long> {

}
