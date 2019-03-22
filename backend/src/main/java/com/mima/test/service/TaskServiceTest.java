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

import com.pCarpet.converter.TaskConverter;
import com.pCarpet.dao.TaskDAO;
import com.pCarpet.dto.TaskDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.Task;
import com.pCarpet.services.TaskService;

public class TaskServiceTest {
	
	@Mock
	private TaskDAO taskDAO;
	
	@InjectMocks
	private TaskService taskService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertTask() {
		
		TaskDTO taskDTO = new TaskDTO(1,"prova",1);
		Task task = TaskConverter.convertToEntity(taskDTO);
		
		when(taskDAO.saveAndFlush(task)).thenReturn(task);
		assertThat(taskService.insertTask(taskDTO), is (taskDTO));
	}
	
	@Test
	public void testGetAllTasksByIdMachine() {
		
		Machine machine = new Machine();
		machine.setId(1);
		
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		tasksDTO.add(new TaskDTO(1,"prova",1));
		tasksDTO.add(new TaskDTO(1,"prova",1));
		tasksDTO.add(new TaskDTO(1,"prova",1));
		tasksDTO.add(new TaskDTO(1,"prova",1));
		List<Task> tasks = TaskConverter.toListEntity(tasksDTO);
		
		when(taskDAO.findAllByMachine(machine)).thenReturn(tasks);
		assertThat(taskService.getAllTasks(machine.getId()), is (tasksDTO));
	}

}
