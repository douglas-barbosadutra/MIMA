package com.mima.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.InstructionConverter;
import com.mima.converter.TaskConverter;
import com.mima.dao.InstructionDAO;
import com.mima.dto.InstructionDTO;
import com.mima.dto.TaskDTO;
import com.mima.model.Instruction;

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
