package com.mima.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
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

import com.mima.services.InstructionService;
import com.mima.services.ItemService;
import com.mima.services.ManufacturingService;
import com.mima.services.TimeService;

import com.mima.dto.InstructionDTO;
import com.mima.dto.ItemDTO;
import com.mima.dto.ManufacturingDTO;
import com.mima.dto.TimeDTO;

public class TimeServiceTest {
	
	@Mock
	private InstructionService instructionService;
	
	@Mock
	private ManufacturingService manufacturingService;
	
	@Mock
	private ItemService itemService;
	
	@InjectMocks
	private TimeService timeService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testFetAllTimes() {
		
		ItemDTO itemDTO = new ItemDTO(1,"prova",0,1,null);
		
		List<InstructionDTO> instructionsDTO = new ArrayList<InstructionDTO>();
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(2,0,"prova","prova",1));
		
		List<ManufacturingDTO> manufacturings1DTO = new ArrayList<ManufacturingDTO>();
		List<ManufacturingDTO> manufacturings2DTO = new ArrayList<ManufacturingDTO>();
		manufacturings1DTO.add(new ManufacturingDTO(1,0,1,1));
		manufacturings1DTO.add(new ManufacturingDTO(2,0,1,1));
		manufacturings2DTO.add(new ManufacturingDTO(3,0,1,2));
		manufacturings2DTO.add(new ManufacturingDTO(4,0,1,2));
		
		List<TimeDTO> timesDTO = new ArrayList<TimeDTO>();
		timesDTO.add(new TimeDTO("prova","prova",0,0,"#79fc00"));
		timesDTO.add(new TimeDTO("prova","prova",0,0,"#79fc00"));
		timesDTO.add(new TimeDTO("prova","prova",0,0,"#79fc00"));
		timesDTO.add(new TimeDTO("prova","prova",0,0,"#79fc00"));
		
		when(instructionService.getAllIstruzioniByIdTask(1)).thenReturn(instructionsDTO);
		
		when(manufacturingService.getManufacturingByInstruction(instructionsDTO.get(0))).thenReturn(manufacturings1DTO);
		when(manufacturingService.getManufacturingByInstruction(instructionsDTO.get(1))).thenReturn(manufacturings2DTO);
	
		for(ManufacturingDTO manufacturing: manufacturings1DTO)
			when(itemService.getItemById(manufacturing.getItem())).thenReturn(itemDTO);
		
		for(ManufacturingDTO manufacturing: manufacturings2DTO)
			when(itemService.getItemById(manufacturing.getItem())).thenReturn(itemDTO);
		
		assertThat(timeService.getAllTempi(1), is (timesDTO));

	}

}
