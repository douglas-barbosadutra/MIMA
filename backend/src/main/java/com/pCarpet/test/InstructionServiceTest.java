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
import com.pCarpet.converter.TaskConverter;
import com.pCarpet.dao.InstructionDAO;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Task;
import com.pCarpet.services.InstructionService;

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
