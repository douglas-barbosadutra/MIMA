package com.mima.test.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mima.converter.InstructionConverter;
import com.mima.converter.ItemConverter;
import com.mima.converter.MachineConverter;
import com.mima.converter.ManufacturingConverter;
import com.mima.converter.TaskConverter;
import com.mima.converter.UserConverter;
import com.mima.converter.WBSConverter;
import com.mima.dao.InstructionDAO;
import com.mima.dao.ItemDAO;
import com.mima.dao.MachineDAO;
import com.mima.dao.ManufacturingDAO;
import com.mima.dao.TaskDAO;
import com.mima.dao.UserDAO;
import com.mima.dao.WBSDAO;
import com.mima.dto.InstructionDTO;
import com.mima.dto.ItemDTO;
import com.mima.dto.MachineDTO;
import com.mima.dto.ManufacturingDTO;
import com.mima.dto.TaskDTO;
import com.mima.dto.UserDTO;
import com.mima.dto.WBSDTO;
import com.mima.model.Instruction;
import com.mima.model.Item;
import com.mima.model.Manufacturing;
import com.mima.model.WBS;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ManufacturingDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MachineDAO machineDAO;
	
	@Autowired
	private WBSDAO wbsDAO;
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private InstructionDAO instructionDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private ManufacturingDAO manufacturingDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		manufacturingDAO.deleteAll();
		itemDAO.deleteAll();
		instructionDAO.deleteAll();
		taskDAO.deleteAll();
		wbsDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testInsertManufacturing() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(1,"prova",idUserInsert);
		int idWbsInsert = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO)).getId();
		
		ItemDTO itemDTO = new ItemDTO(1,"prova",0,idWbsInsert,null);
		int idItemInsert = itemDAO.saveAndFlush(ItemConverter.convertToEntity(itemDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(1,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",idTaskInsert);
		int idInstructionInsert = instructionDAO.saveAndFlush(InstructionConverter.convertToEntity(instructionDTO)).getId();
		
		ManufacturingDTO manufacturingDTO = new ManufacturingDTO(1,0,idItemInsert,idInstructionInsert);
		int idManufacturingInsert = manufacturingDAO.saveAndFlush(ManufacturingConverter.convertToEntity(manufacturingDTO)).getId();
		
		Optional<Manufacturing> manufacturing = manufacturingDAO.findById(idManufacturingInsert);
		
		manufacturingDAO.deleteAll();
		itemDAO.deleteAll();
		instructionDAO.deleteAll();
		taskDAO.deleteAll();
		wbsDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
		
		Assert.assertTrue(manufacturing.isPresent());
	}

	@Test
	public void testDeleteManufacturing() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(1,"prova",idUserInsert);
		WBS wbs = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO));
		
		ItemDTO itemDTO = new ItemDTO(1,"prova",0,wbs.getId(),null);
		int idItemInsert = itemDAO.saveAndFlush(ItemConverter.convertToEntity(itemDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(1,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",idTaskInsert);
		int idInstructionInsert = instructionDAO.saveAndFlush(InstructionConverter.convertToEntity(instructionDTO)).getId();
		
		ManufacturingDTO manufacturingDTO = new ManufacturingDTO(1,0,idItemInsert,idInstructionInsert);
		int idManufacturingInsert = manufacturingDAO.saveAndFlush(ManufacturingConverter.convertToEntity(manufacturingDTO)).getId();
		
		manufacturingDAO.deleteById(idManufacturingInsert);
		Optional<Manufacturing> manufacturing = manufacturingDAO.findById(idManufacturingInsert);
		
		manufacturingDAO.deleteAll();
		itemDAO.deleteAll();
		instructionDAO.deleteAll();
		taskDAO.deleteAll();
		wbsDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
		
		Assert.assertTrue(!manufacturing.isPresent());
	}
	
	@Test
	public void testFindAllByInstruction() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(1,"prova",idUserInsert);
		WBS wbs = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO));
		
		ItemDTO itemDTO = new ItemDTO(1,"prova",0,wbs.getId(),null);
		int idItemInsert = itemDAO.saveAndFlush(ItemConverter.convertToEntity(itemDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(1,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",idTaskInsert);
		Instruction instruction = instructionDAO.saveAndFlush(InstructionConverter.convertToEntity(instructionDTO));
		
		List<ManufacturingDTO> manufacturingsDTO = new ArrayList<ManufacturingDTO>();
		manufacturingsDTO.add(new ManufacturingDTO(0,1,idItemInsert,instruction.getId()));
		manufacturingsDTO.add(new ManufacturingDTO(0,2,idItemInsert,instruction.getId()));
		manufacturingsDTO.add(new ManufacturingDTO(0,3,idItemInsert,instruction.getId()));
		manufacturingsDTO.add(new ManufacturingDTO(0,4,idItemInsert,instruction.getId()));
		
		for(ManufacturingDTO manufacturing: manufacturingsDTO) 
			manufacturingDAO.saveAndFlush(ManufacturingConverter.convertToEntity(manufacturing));			
		
		List<Manufacturing> manufacturings = manufacturingDAO.findAllByInstruction(instruction);
		
		manufacturingDAO.deleteAll();
		itemDAO.deleteAll();
		instructionDAO.deleteAll();
		taskDAO.deleteAll();
		wbsDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
		
		Assert.assertTrue(manufacturingsDTO.size() == manufacturings.size());
		
	}
}
