package com.mima.machine.repository;

import com.mima.machine.domain.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findAllByIdBusinessOwner(Integer id);
	Employee findByIdUser(Integer idUser);
}
