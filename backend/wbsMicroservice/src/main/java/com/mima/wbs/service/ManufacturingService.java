package com.mima.wbs.service;

import com.mima.wbs.service.dto.InstructionDTO;
import com.mima.wbs.service.dto.ManufacturingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Manufacturing.
 */
public interface ManufacturingService {

    /**
     * Save a manufacturing.
     *
     * @param manufacturingDTO the entity to save
     * @return the persisted entity
     */
    ManufacturingDTO save(ManufacturingDTO manufacturingDTO);

    /**
     * Get all the manufacturings.
     *
     * @return the list of entities
     */
    List<ManufacturingDTO> findAll();


    /**
     * Get the "id" manufacturing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ManufacturingDTO> findOne(Long id);

    /**
     * Delete the "id" manufacturing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	List<ManufacturingDTO> getManufacturingByInstruction(InstructionDTO instruction);
}
