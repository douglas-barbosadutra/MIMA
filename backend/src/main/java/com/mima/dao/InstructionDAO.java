package com.mima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mima.model.Instruction;
import com.mima.model.Task;

import java.util.List;

public interface InstructionDAO extends JpaRepository<Instruction, Integer>{
	public List<Instruction> findAllByTask(Task t); 
}
