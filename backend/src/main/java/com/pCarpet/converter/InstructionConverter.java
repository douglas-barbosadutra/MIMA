package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Task;

public class InstructionConverter{

	public static InstructionDTO convertToDto(Instruction instruction) {
		InstructionDTO instructionDTO = null;
		if(instruction != null) {
			instructionDTO = new InstructionDTO();
			instructionDTO.setId(instruction.getId());
			instructionDTO.setDurata(instruction.getDuration());
			instructionDTO.setCodice(instruction.getGcodeFile());
			instructionDTO.setNomeIstruzione(instruction.getName());
			instructionDTO.setIdTask(instruction.getTask().getId());
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
			Task task = new Task();
			task.setId(instructionDTO.getIdTask());
			instruction.setTask(task);
		}
		return instruction;
	}
	
	public static List<InstructionDTO> toListDTO(List<Instruction> list){
		List<InstructionDTO> listInstructionDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Instruction instruction : list) {
				listInstructionDTO.add(InstructionConverter.convertToDto(instruction));
			}
		}
		return listInstructionDTO;
	}
}
