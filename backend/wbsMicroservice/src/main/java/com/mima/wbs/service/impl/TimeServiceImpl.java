package com.mima.wbs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mima.wbs.service.InstructionService;
import com.mima.wbs.service.ItemService;
import com.mima.wbs.service.ManufacturingService;
import com.mima.wbs.service.TimeService;
import com.mima.wbs.service.dto.InstructionDTO;
import com.mima.wbs.service.dto.ItemDTO;
import com.mima.wbs.service.dto.ManufacturingDTO;
import com.mima.wbs.service.dto.TimeDTO;
import com.mima.wbs.service.mapper.TimeConverter;

@Service
@Transactional
public class TimeServiceImpl implements TimeService{

	private final Logger log = LoggerFactory.getLogger(TimeServiceImpl.class);

	private InstructionService instructionService;
	
	private ManufacturingService manufacturingService;
	
	private ItemService itemService;
	
	
	
	public TimeServiceImpl(InstructionService instructionService, ManufacturingService manufacturingService,
			ItemService itemService) {

		this.instructionService = instructionService;
		this.manufacturingService = manufacturingService;
		this.itemService = itemService;
	}



	@Override
	public List<TimeDTO> showTimeByIdTask(Integer idTask) {
		List<TimeDTO> tempiDTO = new ArrayList<>();
		List<InstructionDTO> istruzioni = instructionService.getAllInstructionByIdTask(idTask);
		for(InstructionDTO istruzione : istruzioni) {
			List<ManufacturingDTO> lavorazioni = 	manufacturingService.getManufacturingByInstruction(istruzione);
			for(ManufacturingDTO lavorazione : lavorazioni) {
				Optional<ItemDTO> item = itemService.findOne(lavorazione.getOutputId());
				if(item.isPresent())
					tempiDTO.add(TimeConverter.convertToDTO(istruzione, lavorazione, item.get()));
			}
			
		}
		return tempiDTO;
	}

}
