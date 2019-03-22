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

import com.mima.converter.InstructionConverter;
import com.mima.converter.ManufacturingConverter;
import com.mima.dao.ManufacturingDAO;
import com.mima.dto.InstructionDTO;
import com.mima.dto.ManufacturingDTO;
import com.mima.model.Instruction;
import com.mima.model.Manufacturing;
import com.mima.services.ManufacturingService;

public class ManufacturingServiceTest {
	
	@Mock
	private ManufacturingDAO manufacturingDAO;
	
	@InjectMocks
	private ManufacturingService manufacturingService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testGetManufacturingByInstruction() {
		
		InstructionDTO instructionDTO = new InstructionDTO();
		instructionDTO.setId(1);
		Instruction instruction = InstructionConverter.convertToEntity(instructionDTO);
		
		List<ManufacturingDTO> manufacturingsDTO = new ArrayList<ManufacturingDTO>();
		manufacturingsDTO.add(new ManufacturingDTO(0,0,0,1));
		manufacturingsDTO.add(new ManufacturingDTO(0,0,0,1));
		manufacturingsDTO.add(new ManufacturingDTO(0,0,0,1));
		manufacturingsDTO.add(new ManufacturingDTO(0,0,0,1));
		List<Manufacturing> manufacturings = ManufacturingConverter.toListEntity(manufacturingsDTO);
		
		when(manufacturingDAO.findAllByInstruction(instruction)).thenReturn(manufacturings);
		assertThat(manufacturingService.getManufacturingByInstruction(instructionDTO), is (manufacturingsDTO));
	}

}
