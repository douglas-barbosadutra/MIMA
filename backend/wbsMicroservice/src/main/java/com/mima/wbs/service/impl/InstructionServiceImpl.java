package com.mima.wbs.service.impl;

import com.mima.wbs.service.InstructionService;
import com.mima.wbs.domain.Instruction;
import com.mima.wbs.repository.InstructionRepository;
import com.mima.wbs.service.dto.InstructionDTO;
import com.mima.wbs.service.mapper.InstructionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Instruction.
 */
@Service
@Transactional
public class InstructionServiceImpl implements InstructionService {

	private final Logger log = LoggerFactory.getLogger(InstructionServiceImpl.class);

	private final InstructionRepository instructionRepository;

	private final InstructionMapper instructionMapper;

	public InstructionServiceImpl(InstructionRepository instructionRepository, InstructionMapper instructionMapper) {
		this.instructionRepository = instructionRepository;
		this.instructionMapper = instructionMapper;
	}

	/**
	 * Save a instruction.
	 *
	 * @param instructionDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public InstructionDTO save(InstructionDTO instructionDTO) {
		log.debug("Request to save Instruction : {}", instructionDTO);
		Instruction instruction = instructionMapper.toEntity(instructionDTO);
		instruction = instructionRepository.save(instruction);
		return instructionMapper.toDto(instruction);
	}

	/**
	 * Get all the instructions.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InstructionDTO> findAll() {
		log.debug("Request to get all Instructions");
		return instructionRepository.findAll().stream().map(instructionMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one instruction by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<InstructionDTO> findOne(Long id) {
		log.debug("Request to get Instruction : {}", id);
		return instructionRepository.findById(id).map(instructionMapper::toDto);
	}

	/**
	 * Delete the instruction by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Instruction : {}", id);
		instructionRepository.deleteById(id);
	}

	@Override
	public List<InstructionDTO> getAllInstructionByIdTask(int idTask) {
		return instructionRepository.findAllByIdTask(idTask).stream().map(instructionMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}
}
