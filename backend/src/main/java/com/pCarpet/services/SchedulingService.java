package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.MachineConverter;
import com.pCarpet.converter.SchedulingConverter;
import com.pCarpet.dao.SchedulingDAO;
import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.model.Scheduling;

@Service
public class SchedulingService {
	
	@Autowired
	private SchedulingDAO schedulingDAO;
	
	public SchedulingService() {	}
	
	public SchedulingDTO getSchedulingById(int id) {
		return (SchedulingConverter.convertToDTO(schedulingDAO.findById(id)));
	}
	
	public SchedulingDTO insertScheduling(SchedulingDTO schedulingDTO) {
		Scheduling scheduling = SchedulingConverter.convertToEntity(schedulingDTO);
		schedulingDAO.saveAndFlush(scheduling);
		return SchedulingConverter.convertToDTO(scheduling);
	}
	
	public boolean deleteScheduling(int idScheduling) {
		schedulingDAO.deleteById(idScheduling);
		return true;
	}
	
	public List<SchedulingDTO> getAllScheduling(int idMachine){
		MachineDTO machine = new MachineDTO();
		machine.setId(idMachine);
		return (SchedulingConverter.toListDTO(schedulingDAO.findAllByMachine(MachineConverter.convertToEntity(machine))));
	}

}