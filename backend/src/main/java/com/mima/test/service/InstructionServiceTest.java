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
import com.mima.converter.TaskConverter;
import com.mima.dao.InstructionDAO;
import com.mima.dto.InstructionDTO;
import com.mima.dto.TaskDTO;
import com.mima.model.Instruction;
import com.mima.model.Task;
import com.mima.services.InstructionService;

public class InstructionServiceTest {
	
	@Mock
	private InstructionDAO instructionDAO;
	
	@InjectMocks
	private InstructionService instructionService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertInstruction() {
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",1);
		Instruction instruction = InstructionConverter.convertToEntity(instructionDTO);
		
		when(instructionDAO.saveAndFlush(instruction)).thenReturn(instruction);
		assertThat(instructionService.insertIstruzione(instructionDTO), is (instructionDTO));
	}
	
	@Test
	public void testGetAllInstructionByIdTask() {
		List<InstructionDTO> instructionsDTO = new ArrayList<InstructionDTO>();
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		List<Instruction> instructions = InstructionConverter.toListEntity(instructionsDTO);
		
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setId(1);
		Task task = TaskConverter.convertToEntity(taskDTO);
		
		when(instructionDAO.findAllByTask(task)).thenReturn(instructions);
		assertThat(instructionService.getAllIstruzioniByIdTask(task.getId()), is (instructionsDTO));
	}
	
	@Test
	public void testDeleteInstruction() {
		
	}

}
