package com.mima.wbs.service.mapper;

import com.mima.wbs.service.dto.InstructionDTO;
import com.mima.wbs.service.dto.ItemDTO;
import com.mima.wbs.service.dto.ManufacturingDTO;
import com.mima.wbs.service.dto.TimeDTO;

public class TimeMapper {
	
	public static TimeDTO convertToDTO(InstructionDTO instructionDTO, ManufacturingDTO manufacturingDTO, ItemDTO itemDTO) {
		TimeDTO timeDTO = null;
		if(instructionDTO != null && manufacturingDTO != null && itemDTO != null) {
			timeDTO = new TimeDTO();
			timeDTO.setNameInstruction(instructionDTO.getName());
			timeDTO.setItem(itemDTO.getName());
			timeDTO.setEffectiveDuration(manufacturingDTO.getDuration());
			timeDTO.setExpectedDuration(instructionDTO.getDuration());
			String risultato = (timeDTO.getEffectiveDuration() <= timeDTO.getExpectedDuration()) ? "#79fc00" : "RED";
			timeDTO.setResult(risultato);
		}
		return timeDTO;
	}

}
