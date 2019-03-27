package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.InstructionConverter;
import com.mima.converter.ManufacturingConverter;
import com.mima.dao.ManufacturingDAO;
import com.mima.dto.InstructionDTO;
import com.mima.dto.ManufacturingDTO;

@Service
public class ManufacturingService {
	
	@Autowired
	ManufacturingDAO manufacturingDAO;
	
	public ManufacturingService() {	}
	
	public List<ManufacturingDTO> getManufacturingByInstruction(InstructionDTO instruction){
		return (ManufacturingConverter.toListDTO(manufacturingDAO.findAllByInstruction(InstructionConverter.convertToEntity(instruction))));
	}
}
