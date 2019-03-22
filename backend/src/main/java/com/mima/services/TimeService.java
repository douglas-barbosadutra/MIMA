package com.mima.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.TimeConverter;
import com.mima.dto.InstructionDTO;
import com.mima.dto.ItemDTO;
import com.mima.dto.ManufacturingDTO;
import com.mima.dto.TaskDTO;
import com.mima.dto.TimeDTO;

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
