package com.MachineMicroservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MachineMicroservice.model.Machine;
import com.MachineMicroservice.model.Scheduling;

public interface SchedulingDAO extends JpaRepository<Scheduling, Integer>{
	
	public List<Scheduling> findAllByMachine(Machine m);
}
