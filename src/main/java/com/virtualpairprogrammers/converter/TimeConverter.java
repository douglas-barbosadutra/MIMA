package com.virtualpairprogrammers.converter;

import com.virtualpairprogrammers.dto.InstructionDTO;
import com.virtualpairprogrammers.dto.ItemDTO;
import com.virtualpairprogrammers.dto.LavorazioneDTO;
import com.virtualpairprogrammers.dto.TimeDTO;

public class TimeConverter {
	
	public static TimeDTO convertToTimesDTO(InstructionDTO instruction, LavorazioneDTO lavorazione, ItemDTO item) {
		return (new TimeDTO(instruction.getNomeIstruzione(), item.getDescrizione(), lavorazione.getDurata(), instruction.getDurata()));
		
	}
}
