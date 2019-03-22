package com.mima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mima.model.Machine;
import com.mima.model.Task;


public interface TaskDAO extends JpaRepository<Task, Integer>{
	public List<Task> findAllByMachine(Machine m);
}
