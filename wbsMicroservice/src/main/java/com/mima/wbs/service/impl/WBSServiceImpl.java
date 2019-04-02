package com.mima.wbs.service.impl;

import com.mima.wbs.service.WBSService;
import com.mima.wbs.domain.WBS;
import com.mima.wbs.repository.WBSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing WBS.
 */
@Service
@Transactional
public class WBSServiceImpl implements WBSService {

    private final Logger log = LoggerFactory.getLogger(WBSServiceImpl.class);

    private final WBSRepository wBSRepository;

    public WBSServiceImpl(WBSRepository wBSRepository) {
        this.wBSRepository = wBSRepository;
    }

    /**
     * Save a wBS.
     *
     * @param wBS the entity to save
     * @return the persisted entity
     */
    @Override
    public WBS save(WBS wBS) {
        log.debug("Request to save WBS : {}", wBS);
        return wBSRepository.save(wBS);
    }

    /**
     * Get all the wBS.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WBS> findAll() {
        log.debug("Request to get all WBS");
        return wBSRepository.findAll();
    }


    /**
     * Get one wBS by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WBS> findOne(Long id) {
        log.debug("Request to get WBS : {}", id);
        return wBSRepository.findById(id);
    }

    /**
     * Delete the wBS by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WBS : {}", id);
        wBSRepository.deleteById(id);
    }
}
