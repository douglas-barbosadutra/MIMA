package com.pCarpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.SchedulingDTO;
import com.pCarpet.services.SchedulingService;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Scheduling")
public class SchedulingController {

	private SchedulingService schedulingService;

	@Autowired
	public SchedulingController(SchedulingService schedulingService) {
		this.schedulingService = schedulingService;
	}

	@RequestMapping(value = "/insertScheduling", method = RequestMethod.POST)
	public SchedulingDTO insertScheduling(@RequestBody SchedulingDTO schedulingDTO) {
		return schedulingService.insertScheduling(schedulingDTO);
	}

	@RequestMapping(value = "/deleteScheduling", method = RequestMethod.POST)
	public boolean deleteScheduling(@RequestBody SchedulingDTO schedulingDTO) {
		return schedulingService.deleteScheduling(schedulingDTO.getId());
	}

	@RequestMapping(value = "/showScheduling", method = RequestMethod.POST)
	public List<SchedulingDTO> showScheduling(@RequestBody MachineDTO machinedto) {
		return schedulingService.getAllScheduling(machinedto);
	}

}
