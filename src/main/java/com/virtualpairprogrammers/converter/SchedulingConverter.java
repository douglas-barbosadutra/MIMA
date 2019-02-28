package com.virtualpairprogrammers.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.virtualpairprogrammers.domain.Scheduling;
import com.virtualpairprogrammers.dto.SchedulingDTO;

public class SchedulingConverter {
	
	public static SchedulingDTO convertToSchedulingDTO(Scheduling scheduling) {
		SimpleDateFormat input = new SimpleDateFormat("dd-MM-yyyy");
		String dataInizio = input.format(scheduling.getInizio());
		String dataFine = input.format(scheduling.getFine());
		return (new SchedulingDTO(scheduling.getId(), scheduling.getNome(), dataInizio, dataFine, scheduling.getIdMacchinario()));
	}
	
	public static Scheduling convertToScheduling(SchedulingDTO schedulingDTO) {
		Timestamp dataInizio = null;
		Timestamp dataFine = null;
		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    Date inizio = (Date) dateFormat.parse(schedulingDTO.getInizio());
		    Date fine = (Date) dateFormat.parse(schedulingDTO.getFine());
		    dataInizio = new Timestamp(inizio.getTime());
		    dataFine = new Timestamp(fine.getTime());
		    
		} catch(Exception e) {
			System.out.println("eccezione: " + e.getMessage());
		}
		return (new Scheduling(schedulingDTO.getId(), schedulingDTO.getNome(), dataInizio, dataFine, schedulingDTO.getIdMacchinario()));
	}
	
	 
}
