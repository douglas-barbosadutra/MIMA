package com.mima.machine.service;

import com.mima.machine.service.dto.TaskScheduledRelationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TaskScheduledRelation.
 */
public interface TaskScheduledRelationService {

    /**
     * Save a taskScheduledRelation.
     *
     * @param taskScheduledRelationDTO the entity to save
     * @return the persisted entity
     */
    TaskScheduledRelationDTO save(TaskScheduledRelationDTO taskScheduledRelationDTO);

    /**
     * Get all the taskScheduledRelations.
     *
     * @return the list of entities
     */
    List<TaskScheduledRelationDTO> findAll();


    /**
     * Get the "id" taskScheduledRelation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TaskScheduledRelationDTO> findOne(Long id);

    /**
     * Delete the "id" taskScheduledRelation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<TaskScheduledRelationDTO> findAllByTaskScheduled(Long idTaskScheduledDTO);
}
