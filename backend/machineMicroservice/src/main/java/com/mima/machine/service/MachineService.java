package com.mima.machine.service;

import com.mima.machine.service.dto.MachineDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Machine.
 */
public interface MachineService {

    /**
     * Save a machine.
     *
     * @param machineDTO the entity to save
     * @return the persisted entity
     */
    MachineDTO save(MachineDTO machineDTO);

    /**
     * Get all the machines.
     *
     * @return the list of entities
     */
    List<MachineDTO> findAll();


    /**
     * Get the "id" machine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MachineDTO> findOne(Long id);

    /**
     * Delete the "id" machine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
