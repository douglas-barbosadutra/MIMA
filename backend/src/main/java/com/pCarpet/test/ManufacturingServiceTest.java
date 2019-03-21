package com.pCarpet.test;

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

import com.pCarpet.converter.InstructionConverter;
import com.pCarpet.converter.ManufacturingConverter;
import com.pCarpet.dao.ManufacturingDAO;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.ManufacturingDTO;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Manufacturing;
import com.pCarpet.services.ManufacturingService;

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
