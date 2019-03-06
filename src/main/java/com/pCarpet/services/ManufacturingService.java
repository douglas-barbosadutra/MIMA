package com.pCarpet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pCarpet.converter.InstructionConverter;
import com.pCarpet.converter.ManufacturingConverter;
import com.pCarpet.dao.ManufacturingDAO;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.ManufacturingDTO;

@Service
public class ManufacturingService {
	ManufacturingDAO manufacturingDAO;
	
	public ManufacturingService(ManufacturingDAO manufacturingDAO) {
		this.manufacturingDAO = manufacturingDAO;
	}
	
	public List<ManufacturingDTO> getManufacturingByInstruction(InstructionDTO instruction){
		return (ManufacturingConverter.toListDTO(manufacturingDAO.findAllByInstruction(InstructionConverter.convertToEntity(instruction))));
	}
}
