package com.mima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mima.model.Machine;
import com.mima.model.Scheduling;


public interface SchedulingDAO extends JpaRepository<Scheduling, Integer>{
	public List<Scheduling> findAllByMachine(Machine m);
}
