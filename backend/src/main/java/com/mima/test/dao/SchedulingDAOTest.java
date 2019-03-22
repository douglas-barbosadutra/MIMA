package com.mima.test.dao;

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

import com.mima.converter.MachineConverter;
import com.mima.converter.SchedulingConverter;
import com.mima.converter.UserConverter;
import com.mima.dao.MachineDAO;
import com.mima.dao.SchedulingDAO;
import com.mima.dao.UserDAO;
import com.mima.dto.MachineDTO;
import com.mima.dto.SchedulingDTO;
import com.mima.dto.UserDTO;
import com.mima.model.Machine;
import com.mima.model.Scheduling;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SchedulingDAOTest {
	
	@Autowired
	private SchedulingDAO schedulingDAO;
	
	@Autowired
	private MachineDAO machineDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		schedulingDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testInsertScheduling() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		SchedulingDTO schedulingDTO = new SchedulingDTO(1,"prova","prova","prova",idMachineInsert);
		int idSchedulingInsert = schedulingDAO.saveAndFlush(SchedulingConverter.convertToEntity(schedulingDTO)).getId();
		
		Optional<Scheduling> scheduling = schedulingDAO.findById(idSchedulingInsert);
		Assert.assertTrue(scheduling.isPresent());
	}
	
	@Test
	public void testDeleteScheduling() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		SchedulingDTO schedulingDTO = new SchedulingDTO(1,"prova","prova","prova",idMachineInsert);
		int idSchedulingInsert = schedulingDAO.saveAndFlush(SchedulingConverter.convertToEntity(schedulingDTO)).getId();
		
		schedulingDAO.deleteById(idSchedulingInsert);
		Optional<Scheduling> scheduling = schedulingDAO.findById(idSchedulingInsert);
		Assert.assertTrue(!scheduling.isPresent());
	}
	
	@Test
	public void testFindAllByMachine() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		Machine machine = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO));
		
		List<SchedulingDTO> schedulingsDTO = new ArrayList<SchedulingDTO>();
		schedulingsDTO.add(new SchedulingDTO(1,"prova","prova","prova",machine.getId()));
		schedulingsDTO.add(new SchedulingDTO(1,"prova","prova","prova",machine.getId()));
		schedulingsDTO.add(new SchedulingDTO(1,"prova","prova","prova",machine.getId()));
		schedulingsDTO.add(new SchedulingDTO(1,"prova","prova","prova",machine.getId()));
		for(SchedulingDTO scheduling: schedulingsDTO)
			schedulingDAO.saveAndFlush(SchedulingConverter.convertToEntity(scheduling));
		
		List<Scheduling> schedulings = schedulingDAO.findAllByMachine(machine);
		Assert.assertTrue(schedulingsDTO.size() == schedulings.size());
	}

}
