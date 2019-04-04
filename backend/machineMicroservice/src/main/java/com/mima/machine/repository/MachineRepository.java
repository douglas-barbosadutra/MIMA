package com.mima.machine.repository;

import com.mima.machine.domain.Machine;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Machine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
	
	public List<Machine> findAllByIdUser(int idUser);

}
