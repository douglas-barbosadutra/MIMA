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
import com.mima.converter.TaskConverter;
import com.mima.converter.UserConverter;
import com.mima.dao.MachineDAO;
import com.mima.dao.TaskDAO;
import com.mima.dao.UserDAO;
import com.mima.dto.MachineDTO;
import com.mima.dto.TaskDTO;
import com.mima.dto.UserDTO;
import com.mima.model.Machine;
import com.mima.model.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskDAOTest {
	
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
		taskDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testInsertTask() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(0,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		Optional<Task> task = taskDAO.findById(idTaskInsert);
		Assert.assertTrue(task.isPresent());
	}
	
	@Test
	public void testDeleteTask() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(0,"prova",idMachineInsert);
		int idTaskInsert = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO)).getId();
		
		taskDAO.deleteById(idTaskInsert);
		Assert.assertTrue(!taskDAO.findById(idTaskInsert).isPresent());
	}
	
	@Test
	public void testFindAllByMachine() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		Machine machine = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO));
		
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		tasksDTO.add(new TaskDTO(0,"prova",machine.getId()));
		tasksDTO.add(new TaskDTO(0,"prova",machine.getId()));
		tasksDTO.add(new TaskDTO(0,"prova",machine.getId()));
		tasksDTO.add(new TaskDTO(0,"prova",machine.getId()));
		for(TaskDTO task : tasksDTO)
			taskDAO.saveAndFlush(TaskConverter.convertToEntity(task));
		
		List<Task> tasks = taskDAO.findAllByMachine(machine);
		Assert.assertTrue(tasksDTO.size() == tasks.size());
	}

}
