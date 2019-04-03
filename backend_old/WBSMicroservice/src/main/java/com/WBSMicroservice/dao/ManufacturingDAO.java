package com.WBSMicroservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WBSMicroservice.model.Manufacturing;
import com.WBSMicroservice.model.Instruction;


public interface ManufacturingDAO extends JpaRepository<Manufacturing, Integer>{
	
	public List<Manufacturing> findAllByInstruction(Instruction i);
}