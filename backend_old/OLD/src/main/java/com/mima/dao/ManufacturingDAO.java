package com.mima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mima.model.Instruction;
import com.mima.model.Manufacturing;


public interface ManufacturingDAO extends JpaRepository<Manufacturing, Integer>{
	
	public List<Manufacturing> findAllByInstruction(Instruction i);
}