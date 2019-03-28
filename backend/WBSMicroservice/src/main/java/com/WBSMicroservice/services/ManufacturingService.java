package com.WBSMicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WBSMicroservice.converter.InstructionConverter;
import com.WBSMicroservice.converter.ManufacturingConverter;
import com.WBSMicroservice.dao.ManufacturingDAO;
import com.WBSMicroservice.dto.InstructionDTO;
import com.WBSMicroservice.dto.ManufacturingDTO;

@Service
public class ManufacturingService {
	
	@Autowired
	ManufacturingDAO manufacturingDAO;
	
	public ManufacturingService() {	}
	
	public List<ManufacturingDTO> getManufacturingByInstruction(InstructionDTO instruction){
		return (ManufacturingConverter.toListDTO(manufacturingDAO.findAllByInstruction(InstructionConverter.convertToEntity(instruction))));
	}
}
