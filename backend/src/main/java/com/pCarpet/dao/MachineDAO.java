package com.pCarpet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pCarpet.model.Machine;
import com.pCarpet.model.Scheduling;
import com.pCarpet.model.Task;
import com.pCarpet.model.User;


public interface MachineDAO extends JpaRepository<Machine, Integer>{
	public List<Machine> findAllByUser(User u);
	public Machine findByTasks(Task tasks);
	public Machine findBySchedulings(Scheduling s);
}