package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.Machine;
import com.pCarpet.model.Scheduling;


public interface SchedulingDAO extends CrudRepository<Scheduling, Integer>{
	public List<Scheduling> findAllByMachine(Machine m);
	public Scheduling findById(int id);
}
