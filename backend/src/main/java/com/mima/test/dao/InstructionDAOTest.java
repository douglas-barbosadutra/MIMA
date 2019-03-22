package com.pCarpet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pCarpet.converter.InstructionConverter;
import com.pCarpet.converter.MachineConverter;
import com.pCarpet.converter.TaskConverter;
import com.pCarpet.converter.UserConverter;
import com.pCarpet.dao.InstructionDAO;
import com.pCarpet.dao.MachineDAO;
import com.pCarpet.dao.TaskDAO;
import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.InstructionDTO;
import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.Instruction;
import com.pCarpet.model.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InstructionDAOTest {
	
	@Autowired
	private InstructionDAO instructionDAO;
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private MachineDAO machineDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		instructionDAO.deleteAll();
		taskDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testInsertInstruction() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(0,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",idTaskInsert);
		int idInstructionInsert = instructionDAO.saveAndFlush(InstructionConverter.convertToEntity(instructionDTO)).getId();
		
		Optional<Instruction> instruction = instructionDAO.findById(idInstructionInsert);
		Assert.assertTrue(instruction.isPresent());
	}
	
	@Test
	public void testDeleteInstruction() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(0,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",idTaskInsert);
		int idInstructionInsert = instructionDAO.saveAndFlush(InstructionConverter.convertToEntity(instructionDTO)).getId();
		
		instructionDAO.deleteById(idInstructionInsert);
		Optional<Instruction> instruction = instructionDAO.findById(idInstructionInsert);
		Assert.assertTrue(!instruction.isPresent());
	}
	
	@Test
	public void testFindAllByTask() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(0,"prova",idMachineInsert);
		Task task = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO));
		
		List<InstructionDTO> instructionsDTO = new ArrayList<InstructionDTO>();
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",task.getId()));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",task.getId()));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",task.getId()));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",task.getId()));
		for(InstructionDTO instruction: instructionsDTO)
			instructionDAO.saveAndFlush(InstructionConverter.convertToEntity(instruction));
		
		List<Instruction> instructions = instructionDAO.findAllByTask(task);
		Assert.assertTrue(instructionsDTO.size() == instructions.size());
		
	}
}
