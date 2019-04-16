package com.mima.matrix.service;

import com.mima.matrix.domain.Matrix;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Matrix.
 */
public interface MatrixService {

    /**
     * Save a matrix.
     *
     * @param matrix the entity to save
     * @return the persisted entity
     */
    Matrix save(Matrix matrix);

    /**
     * Get all the matrices.
     *
     * @return the list of entities
     */
    List<Matrix> findAll();


    /**
     * Get the "id" matrix.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Matrix> findOne(Long id);

    /**
     * Delete the "id" matrix.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
