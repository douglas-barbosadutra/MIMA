package com.pCarpet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Task;

import java.util.List;

public interface InstructionDAO extends JpaRepository<Instruction, Integer>{
	public List<Instruction> findAllByTask(Task t); 
}
