package com.mima.machine.repository;

import com.mima.machine.domain.Machine;
import com.mima.machine.domain.Scheduling;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Scheduling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

	List<Scheduling> findAllByMachine(Machine m);
}
