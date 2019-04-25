package com.mima.machine.service;

import com.mima.machine.service.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Employee.
 */
public interface EmployeeService {

	/**
	 * Save a employee.
	 *
	 * @param employeeDTO the entity to save
	 * @return the persisted entity
	 */
	EmployeeDTO save(EmployeeDTO employeeDTO);

	/**
	 * Get all the employees.
	 *
	 * @return the list of entities
	 */
	List<EmployeeDTO> findAll();

	/**
	 * Get the "id" employee.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<EmployeeDTO> findOne(Long id);

	/**
	 * Delete the "id" employee.
	 *
	 * @param id the id of the entity
	 */
	boolean delete(Long id);

	List<EmployeeDTO> findAllByBusinessOwner(Integer id);

	EmployeeDTO findByIdUser(Integer idUser);
}
