package com.mima.machine.service.impl;

import com.mima.machine.service.InputService;
import com.mima.machine.domain.Input;
import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.repository.InputRepository;
import com.mima.machine.service.dto.InputDTO;
import com.mima.machine.service.mapper.InputMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Input.
 */
@Service
@Transactional
public class InputServiceImpl implements InputService {

    private final Logger log = LoggerFactory.getLogger(InputServiceImpl.class);

    private final InputRepository inputRepository;

    private final InputMapper inputMapper;

    public InputServiceImpl(InputRepository inputRepository, InputMapper inputMapper) {
        this.inputRepository = inputRepository;
        this.inputMapper = inputMapper;
    }

    /**
     * Save a input.
     *
     * @param inputDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InputDTO save(InputDTO inputDTO) {
        log.debug("Request to save Input : {}", inputDTO);
        Input input = inputMapper.toEntity(inputDTO);
        input = inputRepository.save(input);
        return inputMapper.toDto(input);
    }

    /**
     * Get all the inputs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InputDTO> findAll() {
        log.debug("Request to get all Inputs");
        return inputRepository.findAll().stream()
            .map(inputMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one input by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InputDTO> findOne(Long id) {
        log.debug("Request to get Input : {}", id);
        return inputRepository.findById(id)
            .map(inputMapper::toDto);
    }

    /**
     * Delete the input by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Input : {}", id);
        inputRepository.deleteById(id);
    }

	@Override
	public List<InputDTO> findAllByIdTaskScheduled(Long id) {
		log.debug("Request to get all Inputs by taskScheduled: "+id);
		
		TaskScheduled t = new TaskScheduled();
		t.setId(id);
		
        return inputRepository.findAllByTaskScheduled(t).stream()
            .map(inputMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
	}
}
