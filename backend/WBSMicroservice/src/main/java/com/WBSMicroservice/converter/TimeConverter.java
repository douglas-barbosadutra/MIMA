package com.WBSMicroservice.converter;

import com.WBSMicroservice.dto.InstructionDTO;
import com.WBSMicroservice.dto.ItemDTO;
import com.WBSMicroservice.dto.ManufacturingDTO;
import com.WBSMicroservice.dto.TimeDTO;

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
