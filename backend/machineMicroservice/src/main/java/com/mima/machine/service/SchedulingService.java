package com.mima.machine.service;

import com.mima.machine.service.dto.SchedulingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Scheduling.
 */
public interface SchedulingService {

    /**
     * Save a scheduling.
     *
     * @param schedulingDTO the entity to save
     * @return the persisted entity
     */
    SchedulingDTO save(SchedulingDTO schedulingDTO);

    /**
     * Get all the schedulings.
     *
     * @return the list of entities
     */
    List<SchedulingDTO> findAll();


    /**
     * Get the "id" scheduling.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SchedulingDTO> findOne(Long id);

    /**
     * Delete the "id" scheduling.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
