package com.MachineMicroservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MachineMicroservice.converter.MachineConverter;
import com.MachineMicroservice.converter.SchedulingConverter;
import com.MachineMicroservice.dao.SchedulingDAO;
import com.MachineMicroservice.dto.MachineDTO;
import com.MachineMicroservice.dto.SchedulingDTO;
import com.MachineMicroservice.model.Scheduling;

@Service
public class SchedulingService {
	
	@Autowired
	private SchedulingDAO schedulingDAO;
	
	public SchedulingService() {}
	
	public SchedulingDTO insertScheduling(SchedulingDTO schedulingDTO) {
		Scheduling scheduling = SchedulingConverter.convertToEntity(schedulingDTO);
		return SchedulingConverter.convertToDTO(schedulingDAO.saveAndFlush(scheduling));
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
