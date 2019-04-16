package com.mima.matrix.service.impl;

import com.mima.matrix.service.BlackBoxService;
import com.mima.matrix.domain.BlackBox;
import com.mima.matrix.repository.BlackBoxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing BlackBox.
 */
@Service
@Transactional
public class BlackBoxServiceImpl implements BlackBoxService {

    private final Logger log = LoggerFactory.getLogger(BlackBoxServiceImpl.class);

    private final BlackBoxRepository blackBoxRepository;

    public BlackBoxServiceImpl(BlackBoxRepository blackBoxRepository) {
        this.blackBoxRepository = blackBoxRepository;
    }

    /**
     * Save a blackBox.
     *
     * @param blackBox the entity to save
     * @return the persisted entity
     */
    @Override
    public BlackBox save(BlackBox blackBox) {
        log.debug("Request to save BlackBox : {}", blackBox);
        return blackBoxRepository.save(blackBox);
    }

    /**
     * Get all the blackBoxes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BlackBox> findAll() {
        log.debug("Request to get all BlackBoxes");
        return blackBoxRepository.findAll();
    }


    /**
     * Get one blackBox by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BlackBox> findOne(Long id) {
        log.debug("Request to get BlackBox : {}", id);
        return blackBoxRepository.findById(id);
    }

    /**
     * Delete the blackBox by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BlackBox : {}", id);
        blackBoxRepository.deleteById(id);
    }
}
