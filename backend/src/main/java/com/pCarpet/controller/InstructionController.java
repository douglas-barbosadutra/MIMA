package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.services.InstructionService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Instruction")
public class InstructionController {
	
	private InstructionService istruzioneService;
	
	@Autowired
	public InstructionController(InstructionService is) {
		istruzioneService = is;
	}
	
	@RequestMapping(value="/insertInstruction", method= RequestMethod.POST)
	public InstructionDTO insertInstruction(@RequestBody InstructionDTO instruction) {
		return istruzioneService.insertIstruzione(instruction);
	}
	
	@RequestMapping(value="/deleteInstruction" , method= RequestMethod.POST)
	public boolean  deleteInstruction(@RequestBody InstructionDTO instruction) {		
		return istruzioneService.deleteIstruzione(instruction.getId());
	}
	
	@RequestMapping(value="/showInstruction" , method= RequestMethod.POST)
	public List<InstructionDTO> showInstruction(@RequestBody TaskDTO taskdto) {	
		return istruzioneService.getAllIstruzioni(taskdto);		
	}

}
