package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.Instruction;
import com.virtualpairprogrammers.dto.InstructionDTO;

public class InstructionConverter{

	public static Instruction convertToIstruzione(InstructionDTO istruzionedto, int idTask) {
		return (new Instruction(istruzionedto.getNomeIstruzione(), istruzionedto.getDurata(), idTask, istruzionedto.getId(), istruzionedto.getCodice()));
	}

	public static InstructionDTO convertToDto(Instruction istruzione) {
		return (new InstructionDTO(istruzione.getNome(), istruzione.getDurata(), istruzione.getId(), istruzione.getCodice()));
	}
}
