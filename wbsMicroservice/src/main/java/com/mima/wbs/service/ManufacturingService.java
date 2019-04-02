package com.mima.wbs.service;

import com.mima.wbs.domain.Manufacturing;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Manufacturing.
 */
public interface ManufacturingService {

    /**
     * Save a manufacturing.
     *
     * @param manufacturing the entity to save
     * @return the persisted entity
     */
    Manufacturing save(Manufacturing manufacturing);

    /**
     * Get all the manufacturings.
     *
     * @return the list of entities
     */
    List<Manufacturing> findAll();


    /**
     * Get the "id" manufacturing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Manufacturing> findOne(Long id);

    /**
     * Delete the "id" manufacturing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
