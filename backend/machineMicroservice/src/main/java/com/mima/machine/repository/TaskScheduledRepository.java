package com.mima.machine.repository;

import com.mima.machine.domain.Scheduling;
import com.mima.machine.domain.TaskScheduled;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TaskScheduled entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskScheduledRepository extends JpaRepository<TaskScheduled, Long> {

    @Query(value = "select distinct task_scheduled from TaskScheduled task_scheduled left join fetch task_scheduled.fathers",
        countQuery = "select count(distinct task_scheduled) from TaskScheduled task_scheduled")
    Page<TaskScheduled> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct task_scheduled from TaskScheduled task_scheduled left join fetch task_scheduled.fathers")
    List<TaskScheduled> findAllWithEagerRelationships();

    @Query("select task_scheduled from TaskScheduled task_scheduled left join fetch task_scheduled.fathers where task_scheduled.id =:id")
    Optional<TaskScheduled> findOneWithEagerRelationships(@Param("id") Long id);

    List<TaskScheduled> findAllByScheduling(Scheduling s);
}
