package com.pCarpet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pCarpet.converter.MachineConverter;
import com.pCarpet.converter.SchedulingConverter;
import com.pCarpet.dao.SchedulingDAO;
import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.SchedulingDTO;

@Service
public class SchedulingService {
	
	private SchedulingDAO schedulingDAO;
	
	public SchedulingService(SchedulingDAO schedulingDAO) {
		this.schedulingDAO = schedulingDAO;
	}
	
	public void insertScheduling(SchedulingDTO schedulingDTO) {
		schedulingDAO.save(SchedulingConverter.convertToEntity(schedulingDTO));
	}
	
	public void deleteScheduling(int idScheduling) {
		schedulingDAO.deleteById(idScheduling);
	}
	
	public List<SchedulingDTO> getAllScheduling(MachineDTO machineDTO){
		return (SchedulingConverter.toListDTO(schedulingDAO.findAllByMachine(MachineConverter.convertToEntity(machineDTO))));
	}

}