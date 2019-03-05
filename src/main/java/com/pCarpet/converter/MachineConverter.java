package com.pCarpet.converter;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.model.Machine;

public class MachineConverter{

	public static Machine convertToEntity(MachineDTO machinedto) {
		Machine machine = null;
		if(machinedto != null) {
			machine = new Machine();
			machine.setId(machinedto.getId());
			machine.setModel(machinedto.getModello());
			machine.setName(machinedto.getNome());
		}
		return machine;
	}

	public static MachineDTO convertToDto(Machine machine) {
		MachineDTO machinedto = null;
		if(machine != null) {
			machinedto = new MachineDTO();
			machinedto.setId(machine.getId());
			machinedto.setModello(machine.getModel());
			machinedto.setNome(machine.getName());
		}
		return machinedto;		
	}
}
