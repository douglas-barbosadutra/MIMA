package com.mima.wbs.service.impl;

import com.mima.wbs.service.InstructionService;
import com.mima.wbs.domain.Instruction;
import com.mima.wbs.repository.InstructionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Instruction.
 */
@Service
@Transactional
public class InstructionServiceImpl implements InstructionService {

    private final Logger log = LoggerFactory.getLogger(InstructionServiceImpl.class);

    private final InstructionRepository instructionRepository;

    public InstructionServiceImpl(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    /**
     * Save a instruction.
     *
     * @param instruction the entity to save
     * @return the persisted entity
     */
    @Override
    public Instruction save(Instruction instruction) {
        log.debug("Request to save Instruction : {}", instruction);
        return instructionRepository.save(instruction);
    }

    /**
     * Get all the instructions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Instruction> findAll() {
        log.debug("Request to get all Instructions");
        return instructionRepository.findAll();
    }


    /**
     * Get one instruction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Instruction> findOne(Long id) {
        log.debug("Request to get Instruction : {}", id);
        return instructionRepository.findById(id);
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
}
