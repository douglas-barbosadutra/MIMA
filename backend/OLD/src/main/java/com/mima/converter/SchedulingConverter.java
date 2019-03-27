package com.mima.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.dto.SchedulingDTO;
import com.mima.model.Machine;
import com.mima.model.Scheduling;

public class SchedulingConverter {
	
	public static SchedulingDTO convertToDTO(Scheduling scheduling) {
		SchedulingDTO schedulingDTO = null;
		if(scheduling != null) {
			schedulingDTO = new SchedulingDTO();
			schedulingDTO.setId(scheduling.getId());
			schedulingDTO.setIdMachine(scheduling.getMachine().getId());
			schedulingDTO.setName(scheduling.getName());
			schedulingDTO.setStartDate(scheduling.getStart());
			schedulingDTO.setEndDate(scheduling.getFinish());
		}
		return schedulingDTO;
	}
	
	public static Scheduling convertToEntity(SchedulingDTO schedulingDTO) {
		Scheduling scheduling = null;
		if(schedulingDTO != null) {
			scheduling = new Scheduling();
			scheduling.setId(schedulingDTO.getId());
			scheduling.setName(schedulingDTO.getName());
			scheduling.setStart(schedulingDTO.getStartDate());
			scheduling.setFinish(schedulingDTO.getEndDate());
			Machine machine = new Machine();
			machine.setId(schedulingDTO.getIdMachine());
			scheduling.setMachine(machine);
		}
		return scheduling;
	}
	
	public static List<SchedulingDTO> toListDTO(List<Scheduling> list){
		List<SchedulingDTO> listSchedulingDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Scheduling scheduling : list) {
				listSchedulingDTO.add(SchedulingConverter.convertToDTO(scheduling));
			}
		}
		return listSchedulingDTO;
	}
	
	public static List<Scheduling> toListEntity(List<SchedulingDTO> list){
		List<Scheduling> listScheduling = new ArrayList<>();
		if (!list.isEmpty()) {
			for(SchedulingDTO scheduling : list) {
				listScheduling.add(SchedulingConverter.convertToEntity(scheduling));
			}
		}
		return listScheduling;
	}
}
