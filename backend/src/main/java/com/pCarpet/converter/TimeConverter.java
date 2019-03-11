package com.pCarpet.converter;

import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.ManufacturingDTO;
import com.pCarpet.dto.TimeDTO;

public class TimeConverter {
	
	public static TimeDTO convertToDTO(InstructionDTO instructionDTO, ManufacturingDTO manufacturingDTO, ItemDTO itemDTO) {
		TimeDTO timeDTO = null;
		if(instructionDTO != null && manufacturingDTO != null && itemDTO != null) {
			timeDTO = new TimeDTO();
			timeDTO.setNameInstruction(instructionDTO.getNomeIstruzione());
			timeDTO.setItem(itemDTO.getName());
			timeDTO.setEffectiveDuration(manufacturingDTO.getDurata());
			timeDTO.setExpectedDuration(instructionDTO.getDurata());
			String risultato = (timeDTO.getEffectiveDuration() <= timeDTO.getExpectedDuration()) ? "#79fc00" : "RED";
			timeDTO.setResult(risultato);
		}
		return timeDTO;
	}
}
