package com.pCarpet.converter;

import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.model.Scheduling;

public class SchedulingConverter {
	
	public static SchedulingDTO convertToDTO(Scheduling scheduling) {
		SchedulingDTO schedulingDTO = null;
		if(scheduling != null) {
			schedulingDTO = new SchedulingDTO();
			schedulingDTO.setId(scheduling.getId());
			schedulingDTO.setIdMacchinario(scheduling.getMachine().getId());
			schedulingDTO.setNome(scheduling.getName());
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
			scheduling.setName(schedulingDTO.getNome());
			scheduling.setStart(schedulingDTO.getDataInizio());
			scheduling.setFinish(schedulingDTO.getDataFine());
		}
		return scheduling;
	}
	
	 
}
