package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.MachineConverter;
import com.mima.converter.SchedulingConverter;
import com.mima.dao.SchedulingDAO;
import com.mima.dto.MachineDTO;
import com.mima.dto.SchedulingDTO;
import com.mima.model.Scheduling;

@Service
public class SchedulingService {
	
	@Autowired
	private SchedulingDAO schedulingDAO;
	
	public SchedulingService() {	}
	
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