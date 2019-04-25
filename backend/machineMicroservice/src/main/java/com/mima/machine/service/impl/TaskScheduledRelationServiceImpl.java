package com.mima.machine.service.impl;

import com.mima.machine.service.TaskScheduledRelationService;
import com.mima.converter.TaskScheduledConverter;
import com.mima.machine.domain.TaskScheduledRelation;
import com.mima.machine.repository.TaskScheduledRelationRepository;
import com.mima.machine.service.dto.TaskScheduledDTO;
import com.mima.machine.service.dto.TaskScheduledRelationDTO;
import com.mima.machine.service.mapper.TaskScheduledRelationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TaskScheduledRelation.
 */
@Service
@Transactional
public class TaskScheduledRelationServiceImpl implements TaskScheduledRelationService {

	private final Logger log = LoggerFactory.getLogger(TaskScheduledRelationServiceImpl.class);

	private final TaskScheduledRelationRepository taskScheduledRelationRepository;

	private final TaskScheduledRelationMapper taskScheduledRelationMapper;

	public TaskScheduledRelationServiceImpl(TaskScheduledRelationRepository taskScheduledRelationRepository,
			TaskScheduledRelationMapper taskScheduledRelationMapper) {
		this.taskScheduledRelationRepository = taskScheduledRelationRepository;
		this.taskScheduledRelationMapper = taskScheduledRelationMapper;
	}

	/**
	 * Save a taskScheduledRelation.
	 *
	 * @param taskScheduledRelationDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public TaskScheduledRelationDTO save(TaskScheduledRelationDTO taskScheduledRelationDTO) {
		log.debug("Request to save TaskScheduledRelation : {}", taskScheduledRelationDTO);
		TaskScheduledRelation taskScheduledRelation = taskScheduledRelationMapper.toEntity(taskScheduledRelationDTO);
		taskScheduledRelation = taskScheduledRelationRepository.save(taskScheduledRelation);
		return taskScheduledRelationMapper.toDto(taskScheduledRelation);
	}

	/**
	 * Get all the taskScheduledRelations.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TaskScheduledRelationDTO> findAll() {
		log.debug("Request to get all TaskScheduledRelations");
		return taskScheduledRelationRepository.findAll().stream().map(taskScheduledRelationMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one taskScheduledRelation by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<TaskScheduledRelationDTO> findOne(Long id) {
		log.debug("Request to get TaskScheduledRelation : {}", id);
		return taskScheduledRelationRepository.findById(id).map(taskScheduledRelationMapper::toDto);
	}

	/**
	 * Delete the taskScheduledRelation by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete TaskScheduledRelation : {}", id);
		taskScheduledRelationRepository.deleteById(id);
	}

	@Override
	public List<TaskScheduledRelationDTO> findAllByTaskScheduled(Long idTaskScheduledDTO) {
		log.debug("Request to get TaskScheduledRelationByidTaskScheduledDTO : {}", idTaskScheduledDTO);
		TaskScheduledDTO taskScheduledDTO = new TaskScheduledDTO();
		taskScheduledDTO.setId(idTaskScheduledDTO);
		return taskScheduledRelationRepository
				.findAll().stream()
				.map(taskScheduledRelationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
	}
}
