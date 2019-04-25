package com.mima.machine.repository;

import com.mima.machine.domain.Machine;
import com.mima.machine.domain.Task;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Task entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	public List<Task> findAllByMachine(Machine m);
}
