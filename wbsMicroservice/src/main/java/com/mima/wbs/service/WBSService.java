package com.mima.wbs.service;

import com.mima.wbs.domain.WBS;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing WBS.
 */
public interface WBSService {

    /**
     * Save a wBS.
     *
     * @param wBS the entity to save
     * @return the persisted entity
     */
    WBS save(WBS wBS);

    /**
     * Get all the wBS.
     *
     * @return the list of entities
     */
    List<WBS> findAll();


    /**
     * Get the "id" wBS.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WBS> findOne(Long id);

    /**
     * Delete the "id" wBS.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
