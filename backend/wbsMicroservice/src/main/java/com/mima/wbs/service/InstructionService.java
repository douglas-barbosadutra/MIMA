package com.mima.wbs.service;

import com.mima.wbs.service.dto.InstructionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Instruction.
 */
public interface InstructionService {

    /**
     * Save a instruction.
     *
     * @param instructionDTO the entity to save
     * @return the persisted entity
     */
    InstructionDTO save(InstructionDTO instructionDTO);

    /**
     * Get all the instructions.
     *
     * @return the list of entities
     */
    List<InstructionDTO> findAll();


    /**
     * Get the "id" instruction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InstructionDTO> findOne(Long id);

    /**
     * Delete the "id" instruction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
