package com.pCarpet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.TimeConverter;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.ManufacturingDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.TimeDTO;

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
