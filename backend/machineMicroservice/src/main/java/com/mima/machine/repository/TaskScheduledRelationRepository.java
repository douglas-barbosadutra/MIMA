package com.mima.machine.repository;

import com.mima.machine.domain.TaskScheduled;
import com.mima.machine.domain.TaskScheduledRelation;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskScheduledRelation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskScheduledRelationRepository extends JpaRepository<TaskScheduledRelation, Long> {
	//public List<TaskScheduledRelation> findAllByTaskScheduledFirstOrTaskScheduledSecond(TaskScheduled taskScheduledFirst, TaskScheduled taskScheduledSecond);
}
