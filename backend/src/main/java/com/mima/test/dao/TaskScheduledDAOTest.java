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

import com.mima.converter.InstructionConverter;
import com.mima.converter.MachineConverter;
import com.mima.converter.SchedulingConverter;
import com.mima.converter.TaskConverter;
import com.mima.converter.TaskScheduledConverter;
import com.mima.converter.UserConverter;
import com.mima.dao.MachineDAO;
import com.mima.dao.SchedulingDAO;
import com.mima.dao.TaskDAO;
import com.mima.dao.TaskScheduledDAO;
import com.mima.dao.UserDAO;
import com.mima.dto.InstructionDTO;
import com.mima.dto.MachineDTO;
import com.mima.dto.SchedulingDTO;
import com.mima.dto.TaskDTO;
import com.mima.dto.TaskScheduledDTO;
import com.mima.dto.UserDTO;
import com.mima.model.Instruction;
import com.mima.model.Scheduling;
import com.mima.model.Task;
import com.mima.model.TaskScheduled;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskScheduledDAOTest {

	
	@Autowired
	private TaskScheduledDAO taskScheduledDAO;
	
	@Autowired
	private MachineDAO machineDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private SchedulingDAO schedulingDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		taskScheduledDAO.deleteAll();
		machineDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testFindAllByTask() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",idUserInsert);
		int idMachineInsert = machineDAO.saveAndFlush(MachineConverter.convertToEntity(machineDTO)).getId();
		
		TaskDTO taskDTO = new TaskDTO(0,"prova",idMachineInsert);
		Task task = taskDAO.saveAndFlush(TaskConverter.convertToEntity(taskDTO));
		
		SchedulingDTO schedulingDTO = new SchedulingDTO(0,"prova","prova","prova",idMachineInsert);
		Scheduling scheduling = schedulingDAO.saveAndFlush(SchedulingConverter.convertToEntity(schedulingDTO));
				
		List<TaskScheduledDTO> taskScheduled = new ArrayList<TaskScheduledDTO>();
		taskScheduled.add(new TaskScheduledDTO(1,task.getId(),true,"prova",scheduling.getId(),null ));
		taskScheduled.add(new TaskScheduledDTO(2,task.getId(),true,"prova2",scheduling.getId(),null ));
		for(TaskScheduledDTO tasks: taskScheduled)
			taskScheduledDAO.saveAndFlush(TaskScheduledConverter.convertToEntity(tasks));
		
		List<TaskScheduled> taskScheduleds = taskScheduledDAO.findAllByScheduling(scheduling);
		Assert.assertTrue(taskScheduled.size() == taskScheduleds.size());
		
	}
	
	
	
	
}
