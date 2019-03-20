package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/insertInstruction")
	public ResponseEntity<InstructionDTO> insertInstruction(@RequestBody InstructionDTO instruction) {
		//System.out.println("Instruction: "+instruction);
		return ResponseEntity.status(HttpStatus.OK).body(istruzioneService.insertIstruzione(instruction));
	}
	
	@DeleteMapping("/deleteInstruction")
	public ResponseEntity<Boolean>  deleteInstruction(@RequestParam(value="idInstruction") int idInstruction) {		
		return ResponseEntity.status(HttpStatus.OK).body(istruzioneService.deleteIstruzione(idInstruction));
	}
	
	@GetMapping("/showInstruction")
	public ResponseEntity<List<InstructionDTO>> showInstruction(@RequestParam(value="idTask") int idTask) {	
		return ResponseEntity.status(HttpStatus.OK).body(istruzioneService.getAllIstruzioniByIdTask(idTask));
	}

}
