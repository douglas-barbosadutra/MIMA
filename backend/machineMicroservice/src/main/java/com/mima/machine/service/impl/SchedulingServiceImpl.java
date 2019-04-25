package com.mima.machine.service.impl;

import com.mima.machine.service.SchedulingService;
import com.mima.machine.domain.Machine;
import com.mima.machine.domain.Scheduling;
import com.mima.machine.repository.SchedulingRepository;
import com.mima.machine.service.dto.SchedulingDTO;
import com.mima.machine.service.mapper.SchedulingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Scheduling.
 */
@Service
@Transactional
public class SchedulingServiceImpl implements SchedulingService {

    private final Logger log = LoggerFactory.getLogger(SchedulingServiceImpl.class);

    private final SchedulingRepository schedulingRepository;

    private final SchedulingMapper schedulingMapper;

    public SchedulingServiceImpl(SchedulingRepository schedulingRepository, SchedulingMapper schedulingMapper) {
        this.schedulingRepository = schedulingRepository;
        this.schedulingMapper = schedulingMapper;
    }

    /**
     * Save a scheduling.
     *
     * @param schedulingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SchedulingDTO save(SchedulingDTO schedulingDTO) {
        log.debug("Request to save Scheduling : {}", schedulingDTO);
        Scheduling scheduling = schedulingMapper.toEntity(schedulingDTO);
        scheduling = schedulingRepository.save(scheduling);
        return schedulingMapper.toDto(scheduling);
    }

    /**
     * Get all the schedulings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SchedulingDTO> findAll() {
        log.debug("Request to get all Schedulings");
        return schedulingRepository.findAll().stream()
            .map(schedulingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one scheduling by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SchedulingDTO> findOne(Long id) {
        log.debug("Request to get Scheduling : {}", id);
        return schedulingRepository.findById(id)
            .map(schedulingMapper::toDto);
    }

    /**
     * Delete the scheduling by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Scheduling : {}", id);
        schedulingRepository.deleteById(id);
    }

    @Override
	public List<SchedulingDTO> findAllByIdMachine(Long idMachine) {
		log.debug("Request to get all Schedulings by Machine");
		
		Machine m = new Machine();
		m.setId(idMachine);
		
        return schedulingRepository.findAllByMachine(m).stream()
            .map(schedulingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
	}
}
