package com.mima.converter;

import com.mima.dto.InstructionDTO;
import com.mima.dto.ItemDTO;
import com.mima.dto.ManufacturingDTO;
import com.mima.dto.TimeDTO;

public class TimeConverter {
	
	public static TimeDTO convertToDTO(InstructionDTO instructionDTO, ManufacturingDTO manufacturingDTO, ItemDTO itemDTO) {
		TimeDTO timeDTO = null;
		if(instructionDTO != null && manufacturingDTO != null && itemDTO != null) {
			timeDTO = new TimeDTO();
			timeDTO.setNameInstruction(instructionDTO.getNameInstruction());
			timeDTO.setItem(itemDTO.getName());
			timeDTO.setEffectiveDuration(manufacturingDTO.getDurata());
			timeDTO.setExpectedDuration(instructionDTO.getDuration());
			String risultato = (timeDTO.getEffectiveDuration() <= timeDTO.getExpectedDuration()) ? "#79fc00" : "RED";
			timeDTO.setResult(risultato);
		}
		return timeDTO;
	}
}
