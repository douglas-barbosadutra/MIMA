package com.mima.machine.service.impl;

import com.mima.machine.service.MachineService;
import com.mima.machine.domain.Machine;
import com.mima.machine.repository.MachineRepository;
import com.mima.machine.service.dto.MachineDTO;
import com.mima.machine.service.mapper.MachineMapper;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Service Implementation for managing Machine.
 */
@Service
@Transactional
public class MachineServiceImpl implements MachineService {

	// per criteria
	@PersistenceContext
	private EntityManager entityManager;

	private final Logger log = LoggerFactory.getLogger(MachineServiceImpl.class);

	private final MachineRepository machineRepository;

	private final MachineMapper machineMapper;

	public MachineServiceImpl(MachineRepository machineRepository, MachineMapper machineMapper) {
		this.machineRepository = machineRepository;
		this.machineMapper = machineMapper;
	}

	/**
	 * Save a machine.
	 *
	 * @param machineDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public MachineDTO save(MachineDTO machineDTO) {
		log.debug("Request to save Machine : {}", machineDTO);
		Machine machine = machineMapper.toEntity(machineDTO);
		machine = machineRepository.save(machine);
		return machineMapper.toDto(machine);
	}

	/**
	 * Get all the machines.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MachineDTO> findAll() {
		log.debug("Request to get all Machines");
		return machineRepository.findAll().stream().map(machineMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one machine by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<MachineDTO> findOne(Long id) {
		log.debug("Request to get Machine : {}", id);
		return machineRepository.findById(id).map(machineMapper::toDto);
	}

	/**
	 * Delete the machine by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Machine : {}", id);
		machineRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MachineDTO> findAllMachineByIdUser(int idUser) {
		return machineRepository.findAllByIdUser(idUser).stream().map(machineMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<MachineDTO> TESTfindAllMachineByUserId(int idUser) {
		return machineRepository.TESTfindAllMachineByUserId(idUser).stream().map(machineMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	// test query con criteria
	public String TEST2findAllMachineByUserId() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// criteri selezione
		CriteriaQuery<Machine> cr = cb.createQuery(Machine.class);
		Root<Machine> root = cr.from(Machine.class);
		cr.select(root);

		// estrarre risultati
		Query<Machine> query = (Query<Machine>) entityManager.createQuery(cr);
		List<Machine> results = query.getResultList();

		return results.toString();
	}
}
