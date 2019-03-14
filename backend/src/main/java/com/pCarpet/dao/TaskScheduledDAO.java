package com.pCarpet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pCarpet.model.Scheduling;
import com.pCarpet.model.TaskScheduled;


public interface TaskScheduledDAO extends JpaRepository<TaskScheduled, Integer>{
	public List<TaskScheduled> findAllByScheduling(Scheduling s);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO task_scheduled_relations (child_id, father_id) VALUES (:child_id, :father_id)", nativeQuery=true)
	public void insertScheduledRelations(@Param("child_id") Integer child_id, @Param("father_id") Integer father_id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE task_scheduled set id_item = :id_item WHERE id_operation_scheduling = :id_operation_scheduling", nativeQuery=true)
	public void insertOutput(@Param("id_item") Integer id_item, @Param("id_operation_scheduling") Integer id_operation_scheduling);
}