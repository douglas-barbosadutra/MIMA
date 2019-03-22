package com.mima.test.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mima.controller.TaskController;
import com.mima.dto.MachineDTO;
import com.mima.dto.TaskDTO;
import com.mima.services.TaskService;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)

public class TaskControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TaskService taskService;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	
	@Test
	public void testInsertTask()  throws Exception {
		
		String uri = "/Task/insertTask";
		TaskDTO taskDTO = new TaskDTO(1,"prova",1);
		String inputJson = this.objectToJson(taskDTO);
		
		when(taskService.insertTask(taskDTO)).thenReturn(taskDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(taskDTO));
	}
	
	@Test
	public void testDeleteTask() throws Exception {
		String uri = "/Task/deleteTask?idTask=1";
		boolean result = true;
		when(taskService.deleteTask(1)).thenReturn(result);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(result));
	}
	

	@Test
	public void testShowTask() throws Exception {
		
		String uri = "/Task/showTask?idTask=1";
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		List<MachineDTO> machinesDTO = new ArrayList<MachineDTO>();
		machinesDTO.add(new MachineDTO(1,"prova","prova",1));
		tasksDTO.add(new TaskDTO(1,"prova",1));
		tasksDTO.add(new TaskDTO(2,"prova",1));
		tasksDTO.add(new TaskDTO(3,"prova",1));
		tasksDTO.add(new TaskDTO(4,"prova",1));
		
		when(taskService.getAllTasks(1)).thenReturn(tasksDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(tasksDTO));
	}

	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
}
