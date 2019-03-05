package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pCarpet.model.Machine;
import com.pCarpet.model.Scheduling;
import com.pCarpet.model.Task;
import com.pCarpet.model.User;


public interface MachineDAO extends CrudRepository<Machine, Integer>{
	public List<Machine> findAllByUser(User u);
	public Machine findByTask(Task t);
	public Machine findByScheduling(Scheduling s);
}