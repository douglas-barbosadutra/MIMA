package com.MachineMicroservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MachineMicroservice.model.Machine;
import com.MachineMicroservice.model.Task;

public interface TaskDAO extends JpaRepository<Task, Integer>{
	
	public List<Task> findAllByMachine(Machine m);

}
