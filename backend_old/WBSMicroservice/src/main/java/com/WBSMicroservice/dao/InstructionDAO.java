package com.WBSMicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WBSMicroservice.model.Instruction;

import java.util.List;

public interface InstructionDAO extends JpaRepository<Instruction, Integer>{
	public List<Instruction> findAllByIdTask(Integer idTask); 
}
