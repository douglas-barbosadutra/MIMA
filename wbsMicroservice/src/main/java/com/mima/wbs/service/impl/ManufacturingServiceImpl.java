package com.mima.wbs.service.impl;

import com.mima.wbs.service.ManufacturingService;
import com.mima.wbs.domain.Manufacturing;
import com.mima.wbs.repository.ManufacturingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Manufacturing.
 */
@Service
@Transactional
public class ManufacturingServiceImpl implements ManufacturingService {

    private final Logger log = LoggerFactory.getLogger(ManufacturingServiceImpl.class);

    private final ManufacturingRepository manufacturingRepository;

    public ManufacturingServiceImpl(ManufacturingRepository manufacturingRepository) {
        this.manufacturingRepository = manufacturingRepository;
    }

    /**
     * Save a manufacturing.
     *
     * @param manufacturing the entity to save
     * @return the persisted entity
     */
    @Override
    public Manufacturing save(Manufacturing manufacturing) {
        log.debug("Request to save Manufacturing : {}", manufacturing);
        return manufacturingRepository.save(manufacturing);
    }

    /**
     * Get all the manufacturings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Manufacturing> findAll() {
        log.debug("Request to get all Manufacturings");
        return manufacturingRepository.findAll();
    }


    /**
     * Get one manufacturing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Manufacturing> findOne(Long id) {
        log.debug("Request to get Manufacturing : {}", id);
        return manufacturingRepository.findById(id);
    }

    /**
     * Delete the manufacturing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Manufacturing : {}", id);
        manufacturingRepository.deleteById(id);
    }
}
