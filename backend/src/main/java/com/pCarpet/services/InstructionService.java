package com.pCarpet.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.InstructionConverter;
import com.pCarpet.converter.TaskConverter;
import com.pCarpet.dao.InstructionDAO;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.TaskDTO;

@Service
public class InstructionService {
	
	private InstructionDAO istruzioneDAO;
	
	@Autowired
	public InstructionService(InstructionDAO istruzioneDAO) {
		this.istruzioneDAO = istruzioneDAO;
	}
	
	public List<InstructionDTO> getAllIstruzioni(TaskDTO task){
		return (InstructionConverter.toListDTO(istruzioneDAO.findAllByTask(TaskConverter.convertToEntity(task))));
	}
	
	public void insertIstruzione(InstructionDTO istruzionedto) {
		this.istruzioneDAO.save(InstructionConverter.convertToEntity(istruzionedto));
	}
	
	public void deleteIstruzione(int idIstruzione) {
		istruzioneDAO.deleteById(idIstruzione);
	}
}
