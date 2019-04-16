package com.mima.matrix.service;

import com.mima.matrix.domain.BlackBox;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BlackBox.
 */
public interface BlackBoxService {

    /**
     * Save a blackBox.
     *
     * @param blackBox the entity to save
     * @return the persisted entity
     */
    BlackBox save(BlackBox blackBox);

    /**
     * Get all the blackBoxes.
     *
     * @return the list of entities
     */
    List<BlackBox> findAll();


    /**
     * Get the "id" blackBox.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BlackBox> findOne(Long id);

    /**
     * Delete the "id" blackBox.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
