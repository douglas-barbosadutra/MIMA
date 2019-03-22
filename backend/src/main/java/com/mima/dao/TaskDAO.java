package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pCarpet.model.Machine;
import com.pCarpet.model.Task;


public interface TaskDAO extends JpaRepository<Task, Integer>{
	public List<Task> findAllByMachine(Machine m);
}
