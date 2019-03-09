package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.Machine;
import com.pCarpet.model.Task;


public interface TaskDAO extends CrudRepository<Task, Integer>{
	public List<Task> findAllByMachine(Machine m);
}
