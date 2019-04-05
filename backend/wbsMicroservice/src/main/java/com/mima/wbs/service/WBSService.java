package com.mima.wbs.service;

import com.mima.wbs.service.dto.WBSDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing WBS.
 */
public interface WBSService {

    /**
     * Save a wBS.
     *
     * @param wBSDTO the entity to save
     * @return the persisted entity
     */
    WBSDTO save(WBSDTO wBSDTO);

    /**
     * Get all the wBS.
     *
     * @return the list of entities
     */
    List<WBSDTO> findAll();


    /**
     * Get the "id" wBS.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WBSDTO> findOne(Long id);

    /**
     * Delete the "id" wBS.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	List<WBSDTO> getWBSByIdUser(int idUser);
}
