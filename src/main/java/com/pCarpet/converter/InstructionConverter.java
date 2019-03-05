package com.pCarpet.converter;

import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.model.Instruction;

public class InstructionConverter{

	public static InstructionDTO convertToDto(Instruction instruction) {
		InstructionDTO instructionDTO = null;
		if(instruction != null) {
			instructionDTO = new InstructionDTO();
			instructionDTO.setId(instruction.getId());
			instructionDTO.setNomeIstruzione(instruction.getName());
			instructionDTO.setDurata(instruction.getDuration());
			instructionDTO.setCodice(instruction.getGcodeFile());
		}
		return instructionDTO;
	}
	
	public static Instruction convertToEntity(InstructionDTO instructionDTO) {
		Instruction instruction = null;
		if(instructionDTO != null) {
			instruction = new Instruction();
			instruction.setId(instructionDTO.getId());
			instruction.setDuration(instructionDTO.getDurata());
			instruction.setGcodeFile(instructionDTO.getCodice());
			instruction.setName(instructionDTO.getNomeIstruzione());
		}
		return instruction;
	}
}
