package com.mima.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mima.converter.SchedulingConverter;
import com.mima.dao.SchedulingDAO;
import com.mima.dto.SchedulingDTO;
import com.mima.model.Machine;
import com.mima.model.Scheduling;
import com.mima.services.SchedulingService;

public class SchedulingServiceTest {
	
	@Mock
	private SchedulingDAO schedulingDAO;
	
	@InjectMocks
	private SchedulingService schedulingService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertScheduling() {
		
		SchedulingDTO schedulingDTO = new SchedulingDTO(0,"prova","prova","prova",0);
		Scheduling scheduling = SchedulingConverter.convertToEntity(schedulingDTO);
		
		when(schedulingDAO.saveAndFlush(scheduling)).thenReturn(scheduling);
		assertThat(schedulingService.insertScheduling(schedulingDTO), is (schedulingDTO));
	}
	
	/*@Test //non funziona
	public void testGetAllSchedulingByIdMachine() {
		
		Machine machine = new Machine();
		machine.setId(1);
		
		List<SchedulingDTO> schedulingsDTO = new ArrayList<SchedulingDTO>();
		schedulingsDTO.add(new SchedulingDTO(0,"prova","prova","prova",1));
		schedulingsDTO.add(new SchedulingDTO(0,"prova","prova","prova",1));
		schedulingsDTO.add(new SchedulingDTO(0,"prova","prova","prova",1));
		schedulingsDTO.add(new SchedulingDTO(0,"prova","prova","prova",1));
		List<Scheduling> schedulings = SchedulingConverter.toListEntity(schedulingsDTO);
		
		when(schedulingDAO.findAllByMachine(machine)).thenReturn(schedulings);
		assertThat(schedulingService.getAllScheduling(machine.getId()), is (schedulingsDTO));
	}*/
	
	@Test
	public void testDeleteScheduling() {
		
	}

}
