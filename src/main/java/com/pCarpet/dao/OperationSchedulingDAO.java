package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.pCarpet.model.OperationScheduling;
import com.pCarpet.model.Scheduling;
import com.pCarpet.model.Task;


public interface OperationSchedulingDAO extends CrudRepository<OperationScheduling, Integer>{
	public List<OperationScheduling> findAllByScheduling(Scheduling s);
	public OperationScheduling findByTask(Task t);
}