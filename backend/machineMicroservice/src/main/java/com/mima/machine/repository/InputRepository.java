package com.mima.machine.repository;

import com.mima.machine.domain.Input;
import com.mima.machine.domain.TaskScheduled;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Input entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InputRepository extends JpaRepository<Input, Long> {
	List<Input> findAllByTaskScheduled(TaskScheduled t);
}
