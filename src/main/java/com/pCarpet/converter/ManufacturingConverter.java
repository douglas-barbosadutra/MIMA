package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.ManufacturingDTO;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Item;
import com.pCarpet.model.Manufacturing;

public class  ManufacturingConverter{
	
	public static Manufacturing convertToEntity(ManufacturingDTO manufacturingDTO) {
		Manufacturing manufacturing = null;
		if(manufacturingDTO != null) {
			manufacturing = new Manufacturing();
			manufacturing.setId(manufacturingDTO.getId());
			manufacturing.setDuration(manufacturingDTO.getDurata());
			Instruction instruction = new Instruction();
			Item item = new Item();
			instruction.setId(manufacturingDTO.getIstruzione());
			item.setId(manufacturingDTO.getItem());
			manufacturing.setOutput(item);
			manufacturing.setInstruction(instruction);
		}
		return manufacturing;
	}

	public static ManufacturingDTO convertToDto(Manufacturing manufacturing) {
		ManufacturingDTO manufacturingDTO = null;
		if(manufacturing != null) {
			manufacturingDTO = new ManufacturingDTO();
			manufacturingDTO.setId(manufacturing.getId());
			manufacturingDTO.setDurata(manufacturing.getDuration());
			manufacturingDTO.setIstruzione(manufacturing.getInstruction().getId());
			manufacturingDTO.setItem(manufacturing.getOutput().getId());
		}
		return manufacturingDTO;
	}

	public static List<ManufacturingDTO> toListDTO(List<Manufacturing> list){
		List<ManufacturingDTO> listManufacturingDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for(Manufacturing manufacturing : list) {
				listManufacturingDTO.add(ManufacturingConverter.convertToDto(manufacturing));
			}
		}
		return listManufacturingDTO;
	}
}
