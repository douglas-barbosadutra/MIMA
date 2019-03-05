package com.pCarpet.dao;

import org.springframework.data.repository.CrudRepository;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Task;

import java.util.List;

public interface InstructionDAO extends CrudRepository<Instruction, Integer>{
	public List<Instruction> findAllByTask(Task t); 
}
