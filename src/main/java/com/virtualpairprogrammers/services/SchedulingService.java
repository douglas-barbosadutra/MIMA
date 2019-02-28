package com.virtualpairprogrammers.services;

import java.util.ArrayList;
import java.util.List;

import com.virtualpairprogrammers.converter.SchedulingConverter;
import com.virtualpairprogrammers.dao.SchedulingDAO;
import com.virtualpairprogrammers.dto.SchedulingDTO;
import com.virtualpairprogrammers.domain.Scheduling;


public class SchedulingService {
	
	private SchedulingDAO schedulingDAO;
	
	public SchedulingService() {
		this.schedulingDAO = new SchedulingDAO();
	}
	
	public void insertScheduling(SchedulingDTO schedulingDTO) {
		schedulingDAO.insertScheduling(SchedulingConverter.convertToScheduling(schedulingDTO));
	}
	
	public void deleteScheduling(int idScheduling) {
		schedulingDAO.deleteScheduling(idScheduling);
	}
	
	public List<SchedulingDTO> getAllScheduling(int idMacchinario){
		List<Scheduling> listaScheduling = this.schedulingDAO.getSchedulingByMachine(idMacchinario);
		List<SchedulingDTO> schedulingDTO = new ArrayList<>();
		for(Scheduling scheduling : listaScheduling) {
			schedulingDTO.add(SchedulingConverter.convertToSchedulingDTO(scheduling));
		}
		return schedulingDTO;
	}
}