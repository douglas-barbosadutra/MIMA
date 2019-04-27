package com.mima.machine.service;

import com.mima.machine.service.dto.TaskScheduledDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TaskScheduled.
 */
public interface TaskScheduledService {

    /**
     * Save a taskScheduled.
     *
     * @param taskScheduledDTO the entity to save
     * @return the persisted entity
     */
    TaskScheduledDTO save(TaskScheduledDTO taskScheduledDTO);

    /**
     * Get all the taskScheduleds.
     *
     * @return the list of entities
     */
    List<TaskScheduledDTO> findAll();


    /**
     * Get the "id" taskScheduled.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TaskScheduledDTO> findOne(Long id);

    /**
     * Delete the "id" taskScheduled.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<TaskScheduledDTO> findAllByIdScheduling(Long id);
}
