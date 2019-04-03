package com.MachineMicroservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MachineMicroservice.model.Machine;

public interface MachineDAO extends JpaRepository<Machine, Integer>{
	
	public List<Machine> findAllByIdUser(int idUser);

}
