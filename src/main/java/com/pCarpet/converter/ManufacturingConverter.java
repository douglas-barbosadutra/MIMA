package com.pCarpet.converter;

import com.pCarpet.dto.ManufacturingDTO;
import com.pCarpet.model.Manufacturing;

public class  ManufacturingConverter{
	
	public static Manufacturing convertToEntity(ManufacturingDTO manufacturingDTO) {
		Manufacturing manufacturing = null;
		if(manufacturingDTO != null) {
			manufacturing = new Manufacturing();
			manufacturing.setId(manufacturingDTO.getId());
			manufacturing.setDuration(manufacturingDTO.getDurata());
		}
		return manufacturing;
	}

	public static ManufacturingDTO convertToDto(Manufacturing manufacturing) {
		ManufacturingDTO manufacturingDTO = null;
		if(manufacturing != null) {
			manufacturingDTO = new ManufacturingDTO();
			manufacturingDTO.setId(manufacturing.getId());
			manufacturingDTO.setDurata(manufacturing.getDuration());
			//manufacturingDTO.setIstruzione(manufacturing.get);
			//manufacturingDTO.setItem(manufacturing.get);
		}
		return manufacturingDTO;
	}

}
