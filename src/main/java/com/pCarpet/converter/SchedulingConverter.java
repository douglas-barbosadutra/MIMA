package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.Scheduling;

public class SchedulingConverter {
	
	public static SchedulingDTO convertToDTO(Scheduling scheduling) {
		SchedulingDTO schedulingDTO = null;
		if(scheduling != null) {
			schedulingDTO = new SchedulingDTO();
			schedulingDTO.setId(scheduling.getId());
			schedulingDTO.setIdMacchinario(scheduling.getMachine().getId());
			schedulingDTO.setName(scheduling.getName());
			schedulingDTO.setDataInizio(scheduling.getStart());
			schedulingDTO.setDataFine(scheduling.getFinish());
		}
		return schedulingDTO;
	}
	
	public static Scheduling convertToEntity(SchedulingDTO schedulingDTO) {
		Scheduling scheduling = null;
		if(schedulingDTO != null) {
			scheduling = new Scheduling();
			scheduling.setId(schedulingDTO.getId());
			scheduling.setName(schedulingDTO.getName());
			scheduling.setStart(schedulingDTO.getDataInizio());
			scheduling.setFinish(schedulingDTO.getDataFine());
			Machine machine = new Machine();
			machine.setId(schedulingDTO.getIdMacchinario());
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
}
