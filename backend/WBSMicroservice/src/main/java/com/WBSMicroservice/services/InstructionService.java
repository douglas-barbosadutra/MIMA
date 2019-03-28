package com.WBSMicroservice.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WBSMicroservice.converter.InstructionConverter;
import com.WBSMicroservice.dao.InstructionDAO;
import com.WBSMicroservice.dto.InstructionDTO;
import com.WBSMicroservice.model.Instruction;

@Service
public class InstructionService {
	
	@Autowired
	private InstructionDAO istruzioneDAO;
	
	public InstructionService() {	}
	
	public List<InstructionDTO> getAllIstruzioniByIdTask(int idTask){
		return (InstructionConverter.toListDTO(istruzioneDAO.findAllByIdTask(idTask)));
	}
	
	public InstructionDTO insertIstruzione(InstructionDTO istruzionedto) {
		Instruction instruction = InstructionConverter.convertToEntity(istruzionedto);
		this.istruzioneDAO.saveAndFlush(instruction);
		return InstructionConverter.convertToDto(instruction);
	}
	
	public boolean deleteIstruzione(int idIstruzione) {
		istruzioneDAO.deleteById(idIstruzione);
		return true;
	}
}
