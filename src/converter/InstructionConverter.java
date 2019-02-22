package converter;

import dto.*;
import main.model.Instruction;

public class InstructionConverter{

	public static Instruction convertToIstruzione(InstructionDTO istruzionedto) {
		return (new Instruction(istruzionedto.getNomeIstruzione(), istruzionedto.getDurata()));
	}

	public static InstructionDTO convertToDto(Instruction istruzione) {
		return (new InstructionDTO(istruzione.getNome(), istruzione.getDurata()));
	}
}
