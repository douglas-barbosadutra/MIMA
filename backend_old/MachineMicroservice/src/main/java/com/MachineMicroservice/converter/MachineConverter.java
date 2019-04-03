package com.MachineMicroservice.converter;

import java.util.ArrayList;
import java.util.List;

import com.MachineMicroservice.dto.MachineDTO;
import com.MachineMicroservice.model.Machine;

public class MachineConverter {
	
	public static Machine convertToEntity(MachineDTO machineDTO) {
		Machine machine = null;
		
		if(machineDTO != null) {
			
			machine = new Machine();
			
			machine.setId(machineDTO.getId());
			machine.setModel(machineDTO.getModel());
			machine.setName(machineDTO.getName());
			machine.setIdUser(machineDTO.getIdUser());
		}
		return machine;
	}

	public static MachineDTO convertToDto(Machine machine) {
		
		MachineDTO machinedto = null;
		
		if(machine != null) {
			
			machinedto = new MachineDTO();
			
			machinedto.setId(machine.getId());
			machinedto.setModel(machine.getModel());
			machinedto.setName(machine.getName());
			machinedto.setIdUser(machine.getIdUser());
		}
		return machinedto;		
	}
	
	public static List<MachineDTO> toListDTO(List<Machine> list){
		List<MachineDTO> listMachineDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Machine machine : list) {
				listMachineDTO.add(MachineConverter.convertToDto(machine));
			}
		}
		return listMachineDTO;
	}
	
	public static List<Machine> toListEntity(List<MachineDTO> list){
		List<Machine> listMachine = new ArrayList<>();
		if (!list.isEmpty()) {
			for(MachineDTO machine : list) {
				listMachine.add(MachineConverter.convertToEntity(machine));
			}
		}
		return listMachine;
	}
}
