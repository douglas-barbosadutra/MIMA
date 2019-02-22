package converter;

import dto.*;
import main.model.Machine;

public class MachineConverter{

	public static Machine convertToMachine(MachineDTO machinedto) {
		return (new Machine(machinedto.getId(), machinedto.getNome(), machinedto.getModello(), machinedto.getUtente()));
	}

	public static MachineDTO convertToDto(Machine machine) {
		return (new MachineDTO(machine.getId(), machine.getNome(), machine.getModello(), machine.getUtente()));
	}
}
