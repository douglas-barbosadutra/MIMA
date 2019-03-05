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
			timeDTO.setNomeIstruzione(instructionDTO.getNomeIstruzione());
			timeDTO.setItem(itemDTO.getDescrizione());
			timeDTO.setDurataEffettiva(manufacturingDTO.getDurata());
			timeDTO.setDurataPrevista(instructionDTO.getDurata());
			String risultato = (timeDTO.getDurataEffettiva() <= timeDTO.getDurataPrevista()) ? "#79fc00" : "RED";
			timeDTO.setRisultato(risultato);
		}
		return timeDTO;
	}
}
