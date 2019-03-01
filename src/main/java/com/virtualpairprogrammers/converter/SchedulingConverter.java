package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.domain.Scheduling;
import com.virtualpairprogrammers.dto.SchedulingDTO;

public class SchedulingConverter {
	
	public static SchedulingDTO convertToSchedulingDTO(Scheduling scheduling) {
		return (new SchedulingDTO(scheduling.getId(), scheduling.getNome(), scheduling.getInizio(), scheduling.getFine(), scheduling.getIdMacchinario()));
	}
	
	public static Scheduling convertToScheduling(SchedulingDTO schedulingDTO) {
		return (new Scheduling(schedulingDTO.getId(), schedulingDTO.getNome(), schedulingDTO.getInizio(), schedulingDTO.getFine(), schedulingDTO.getIdMacchinario()));
	}
	
	 
}
