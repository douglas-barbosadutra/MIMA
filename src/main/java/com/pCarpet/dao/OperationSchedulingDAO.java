package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pCarpet.model.OperationScheduling;
import com.pCarpet.model.Scheduling;
import com.pCarpet.model.Task;


public interface OperationSchedulingDAO extends CrudRepository<OperationScheduling, Integer>{
	public List<OperationScheduling> findAllByScheduling(Scheduling s);
	public OperationScheduling findByTask(Task t);
	
	@Query(value = "SELECT * FROM operation_scheduling WHERE id_scheduling = :idScheduling ORDER BY order_task", nativeQuery=true)
	public List<OperationScheduling> findAllBySchedulingOrdered(@Param("idScheduling") int idScheduling);	
}