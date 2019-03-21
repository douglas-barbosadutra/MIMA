package com.pCarpet.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.InstructionConverter;
import com.pCarpet.converter.TaskConverter;
import com.pCarpet.dao.InstructionDAO;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Instruction;

@Service
public class InstructionService {
	
	@Autowired
	private InstructionDAO istruzioneDAO;
	
	public InstructionService() {	}
	
	public List<InstructionDTO> getAllIstruzioniByIdTask(int idTask){
		TaskDTO task = new TaskDTO();
		task.setId(idTask);;
		return (InstructionConverter.toListDTO(istruzioneDAO.findAllByTask(TaskConverter.convertToEntity(task))));
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
