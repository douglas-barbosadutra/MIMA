package com.mima.machine.service;

import com.mima.machine.service.dto.TaskScheduledDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Get all the TaskScheduled with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<TaskScheduledDTO> findAllWithEagerRelationships(Pageable pageable);
    
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
}
