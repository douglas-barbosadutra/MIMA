package com.mima.wbs.service.impl;

import com.mima.wbs.service.WBSService;
import com.mima.wbs.domain.WBS;
import com.mima.wbs.repository.WBSRepository;
import com.mima.wbs.service.dto.WBSDTO;
import com.mima.wbs.service.mapper.WBSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing WBS.
 */
@Service
@Transactional
public class WBSServiceImpl implements WBSService {

    private final Logger log = LoggerFactory.getLogger(WBSServiceImpl.class);

    private final WBSRepository wBSRepository;

    private final WBSMapper wBSMapper;

    public WBSServiceImpl(WBSRepository wBSRepository, WBSMapper wBSMapper) {
        this.wBSRepository = wBSRepository;
        this.wBSMapper = wBSMapper;
    }

    /**
     * Save a wBS.
     *
     * @param wBSDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WBSDTO save(WBSDTO wBSDTO) {
        log.debug("Request to save WBS : {}", wBSDTO);
        WBS wBS = wBSMapper.toEntity(wBSDTO);
        wBS = wBSRepository.save(wBS);
        return wBSMapper.toDto(wBS);
    }

    /**
     * Get all the wBS.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WBSDTO> findAll() {
        log.debug("Request to get all WBS");
        return wBSRepository.findAll().stream()
            .map(wBSMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one wBS by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WBSDTO> findOne(Long id) {
        log.debug("Request to get WBS : {}", id);
        return wBSRepository.findById(id)
            .map(wBSMapper::toDto);
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
