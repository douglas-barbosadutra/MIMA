package com.mima.wbs.repository;

import com.mima.wbs.domain.Instruction;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Instruction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {
	public List<Instruction> findAllByIdTask(Integer idTask);
}
