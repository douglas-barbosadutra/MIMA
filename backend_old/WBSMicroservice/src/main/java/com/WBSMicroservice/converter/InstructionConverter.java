package com.WBSMicroservice.converter;

import java.util.ArrayList;
import java.util.List;

import com.WBSMicroservice.dto.InstructionDTO;
import com.WBSMicroservice.model.Instruction;

public class InstructionConverter{

	public static InstructionDTO convertToDto(Instruction instruction) {
		InstructionDTO instructionDTO = null;
		if(instruction != null) {
			instructionDTO = new InstructionDTO();
			instructionDTO.setId(instruction.getId());
			instructionDTO.setDuration(instruction.getDuration());
			instructionDTO.setCodex(instruction.getGcodeFile());
			instructionDTO.setNameInstruction(instruction.getName());
			instructionDTO.setIdTask(instruction.getIdTask());
		}
		return instructionDTO;
	}
	
	public static Instruction convertToEntity(InstructionDTO instructionDTO) {
		Instruction instruction = null;
		if(instructionDTO != null) {
			instruction = new Instruction();
			instruction.setId(instructionDTO.getId());
			instruction.setDuration(instructionDTO.getDuration());
			instruction.setGcodeFile(instructionDTO.getCodex());
			instruction.setName(instructionDTO.getNameInstruction());
			instruction.setIdTask(instructionDTO.getIdTask());
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
	
	public static List<Instruction> toListEntity(List<InstructionDTO> list){
		List<Instruction> listInstruction = new ArrayList<>();
		if (!list.isEmpty()) {
			for(InstructionDTO instruction : list) {
				listInstruction.add(InstructionConverter.convertToEntity(instruction));
			}
		}
		return listInstruction;
	}
}
