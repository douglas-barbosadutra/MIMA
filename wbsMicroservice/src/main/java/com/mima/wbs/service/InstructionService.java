package com.mima.wbs.service;

import com.mima.wbs.domain.Instruction;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Instruction.
 */
public interface InstructionService {

    /**
     * Save a instruction.
     *
     * @param instruction the entity to save
     * @return the persisted entity
     */
    Instruction save(Instruction instruction);

    /**
     * Get all the instructions.
     *
     * @return the list of entities
     */
    List<Instruction> findAll();


    /**
     * Get the "id" instruction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Instruction> findOne(Long id);

    /**
     * Delete the "id" instruction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
