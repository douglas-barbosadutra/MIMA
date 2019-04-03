package com.mima.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.dto.MachineDTO;
import com.mima.model.Machine;
import com.mima.model.User;

public class MachineConverter{

	public static Machine convertToEntity(MachineDTO machinedto) {
		Machine machine = null;
		if(machinedto != null) {
			machine = new Machine();
			machine.setId(machinedto.getId());
			machine.setModel(machinedto.getModel());
			User user = new User();
			user.setId(machinedto.getIdUser());
			machine.setName(machinedto.getName());
			machine.setUser(user);
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
			machinedto.setIdUser(machine.getUser().getId());
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
