package com.mima.wbs.service.impl;

import com.mima.wbs.service.ManufacturingService;
import com.mima.wbs.domain.Manufacturing;
import com.mima.wbs.repository.ManufacturingRepository;
import com.mima.wbs.service.dto.ManufacturingDTO;
import com.mima.wbs.service.mapper.ManufacturingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Manufacturing.
 */
@Service
@Transactional
public class ManufacturingServiceImpl implements ManufacturingService {

    private final Logger log = LoggerFactory.getLogger(ManufacturingServiceImpl.class);

    private final ManufacturingRepository manufacturingRepository;

    private final ManufacturingMapper manufacturingMapper;

    public ManufacturingServiceImpl(ManufacturingRepository manufacturingRepository, ManufacturingMapper manufacturingMapper) {
        this.manufacturingRepository = manufacturingRepository;
        this.manufacturingMapper = manufacturingMapper;
    }

    /**
     * Save a manufacturing.
     *
     * @param manufacturingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ManufacturingDTO save(ManufacturingDTO manufacturingDTO) {
        log.debug("Request to save Manufacturing : {}", manufacturingDTO);
        Manufacturing manufacturing = manufacturingMapper.toEntity(manufacturingDTO);
        manufacturing = manufacturingRepository.save(manufacturing);
        return manufacturingMapper.toDto(manufacturing);
    }

    /**
     * Get all the manufacturings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ManufacturingDTO> findAll() {
        log.debug("Request to get all Manufacturings");
        return manufacturingRepository.findAll().stream()
            .map(manufacturingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one manufacturing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ManufacturingDTO> findOne(Long id) {
        log.debug("Request to get Manufacturing : {}", id);
        return manufacturingRepository.findById(id)
            .map(manufacturingMapper::toDto);
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
