package com.mima.machine.service.impl;

import com.mima.machine.service.TaskScheduledService;
import com.mima.machine.converter.TaskScheduledConverter;
import com.mima.machine.domain.Scheduling;
import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.repository.TaskScheduledRepository;
import com.mima.machine.service.dto.TaskScheduledDTO;
import com.mima.machine.service.mapper.TaskScheduledMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        return taskScheduledRepository.findAll().stream()
            .map(taskScheduledMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
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
        return taskScheduledRepository.findById(id)
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
    
    @Override
   	public List<TaskScheduledDTO> findAllByIdScheduling(Long id) {
   		log.debug("Request to get all TaskScheduleds by scheduling: "+id);
   		Scheduling s = new Scheduling();
   		s.setId(id);
           return TaskScheduledConverter.toListDTO(taskScheduledRepository.findAllByScheduling(s));
   	}
    
    /*
	@Override
	public List<OperationSchedulingDTO> findAllOperationScheduling(Long idScheduling) {
		log.debug("Request to get all operationScheduling by scheduling: "+idScheduling);
		
		List<TaskScheduledDTO> list = this.findAllByIdScheduling(idScheduling);
		List<OperationSchedulingDTO> result = new ArrayList<>();
		for (TaskScheduledDTO task : list) {
			if (task.getTaskScheduledList() != null) {
				for (TaskScheduledDTO child : task.getTaskScheduledList()) {
					OperationSchedulingDTO temp = new OperationSchedulingDTO(task.getId(), child.getId(), 0, 0);
					result.add(temp);
				}
			}
		}
		return result;
	}*/
/*
	@Override
	public void inserOperationScheduling(OperationSchedulingDTO osDTO) {
		log.debug("Request to save OperationScheduling : "+ osDTO);
		
		Optional<TaskScheduled> fatherOptional = taskScheduledRepository.findById(osDTO.getIdFather());
		Optional<TaskScheduled> childOptional = taskScheduledRepository.findById(osDTO.getIdChild());
		TaskScheduled father;
		TaskScheduled child;
		
		if(fatherOptional.isPresent() && childOptional.isPresent()) {
			
			father = fatherOptional.get();
			child = childOptional.get();
			
			TaskScheduledDTO fatherDTO = TaskScheduledConverter.toDto(father);
			TaskScheduledDTO childDTO = TaskScheduledConverter.toDto(child);

			if (osDTO.getIdTask() == 0) {

				if (searchLoop(childDTO, osDTO.getIdFather())) {
					if (searchLoop(fatherDTO, osDTO.getIdChild())) {
						return;
					}
					Long id = osDTO.getIdChild();
					osDTO.setIdChild(osDTO.getIdFather());
					osDTO.setIdFather(id);
				}
			}
			Long idChild = osDTO.getIdChild();
			taskScheduledRepository.insertScheduledRelations(idChild, osDTO.getIdFather());
		}
	}*/
	/*
	private boolean searchLoop(TaskScheduledDTO task, Long id) {
		if (task.getId() == id)
			return true;
		boolean result = false;
		for (TaskScheduledDTO child : task.getTaskScheduledList()) {
			result = result | searchLoop(child, id);
		}
		return result;
	}*/
}
