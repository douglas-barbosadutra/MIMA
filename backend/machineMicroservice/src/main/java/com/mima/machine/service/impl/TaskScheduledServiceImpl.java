package com.mima.machine.service.impl;

import com.mima.machine.service.TaskScheduledService;
import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.repository.TaskScheduledRepository;
import com.mima.machine.service.dto.TaskScheduledDTO;
import com.mima.machine.service.mapper.TaskScheduledMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TaskScheduled.
 */
@Service
@Transactional
public class TaskScheduledServiceImpl implements TaskScheduledService {

    private final Logger log = LoggerFactory.getLogger(TaskScheduledServiceImpl.class);

    private final TaskScheduledRepository taskScheduledRepository;

    private final TaskScheduledMapper taskScheduledMapper;

    public TaskScheduledServiceImpl(TaskScheduledRepository taskScheduledRepository, TaskScheduledMapper taskScheduledMapper) {
        this.taskScheduledRepository = taskScheduledRepository;
        this.taskScheduledMapper = taskScheduledMapper;
    }

    /**
     * Save a taskScheduled.
     *
     * @param taskScheduledDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaskScheduledDTO save(TaskScheduledDTO taskScheduledDTO) {
        log.debug("Request to save TaskScheduled : {}", taskScheduledDTO);
        TaskScheduled taskScheduled = taskScheduledMapper.toEntity(taskScheduledDTO);
        taskScheduled = taskScheduledRepository.save(taskScheduled);
        return taskScheduledMapper.toDto(taskScheduled);
    }

    /**
     * Get all the taskScheduleds.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaskScheduledDTO> findAll() {
        log.debug("Request to get all TaskScheduleds");
        return taskScheduledRepository.findAllWithEagerRelationships().stream()
            .map(taskScheduledMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the TaskScheduled with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<TaskScheduledDTO> findAllWithEagerRelationships(Pageable pageable) {
        return taskScheduledRepository.findAllWithEagerRelationships(pageable).map(taskScheduledMapper::toDto);
    }
    

    /**
     * Get one taskScheduled by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TaskScheduledDTO> findOne(Long id) {
        log.debug("Request to get TaskScheduled : {}", id);
        return taskScheduledRepository.findOneWithEagerRelationships(id)
            .map(taskScheduledMapper::toDto);
    }

    /**
     * Delete the taskScheduled by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskScheduled : {}", id);
        taskScheduledRepository.deleteById(id);
    }
}
