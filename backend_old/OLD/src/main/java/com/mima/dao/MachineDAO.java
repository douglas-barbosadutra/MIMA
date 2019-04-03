package com.mima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mima.model.Machine;
import com.mima.model.Scheduling;
import com.mima.model.Task;
import com.mima.model.User;


public interface MachineDAO extends JpaRepository<Machine, Integer>{
	public List<Machine> findAllByUser(User u);
	
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    int getLastInsertId();
}