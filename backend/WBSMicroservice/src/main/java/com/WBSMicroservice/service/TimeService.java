package com.WBSMicroservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WBSMicroservice.converter.TimeConverter;
import com.WBSMicroservice.dto.InstructionDTO;
import com.WBSMicroservice.dto.ItemDTO;
import com.WBSMicroservice.dto.ManufacturingDTO;
import com.WBSMicroservice.dto.TimeDTO;

@Service
public class TimeService {
	
	@Autowired
	private InstructionService instructionService;
	
	@Autowired
	private ManufacturingService manufacturingService;
	
	@Autowired
	private ItemService itemService;
	
	public TimeService() {	}
	
	public List<TimeDTO> getAllTempi(int idTask){
		List<TimeDTO> tempiDTO = new ArrayList<>();
		List<InstructionDTO> istruzioni = instructionService.getAllIstruzioniByIdTask(idTask);
		for(InstructionDTO istruzione : istruzioni) {
			List<ManufacturingDTO> lavorazioni = 	manufacturingService.getManufacturingByInstruction(istruzione);
			for(ManufacturingDTO lavorazione : lavorazioni) {
				ItemDTO item = itemService.getItemById(lavorazione.getItem());
				tempiDTO.add(TimeConverter.convertToDTO(istruzione, lavorazione, item));
			}
			
		}
		return tempiDTO;
	}
}
