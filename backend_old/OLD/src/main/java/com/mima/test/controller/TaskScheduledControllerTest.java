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
import com.mima.controller.TaskScheduledController;
import com.mima.dto.OperationSchedulingDTO;
import com.mima.dto.TaskScheduledDTO;
import com.mima.services.TaskScheduledService;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskScheduledController.class)
public class TaskScheduledControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TaskScheduledService taskScheduledService;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test //non so come testare dato che restituisce void
	public void testInsertOperationScheduling() throws Exception {
		
		
	}
	
	@Test
	public void testInsertTaskScheduled() throws Exception {
		
		String uri = "/TaskScheduled/insertTaskScheduled";
		TaskScheduledDTO task = new TaskScheduledDTO(1,1,false,"prova",1,null);
		String inputJson = this.objectToJson(task);
		
		when(taskScheduledService.insertTaskScheduled(task)).thenReturn(task);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(task));
	}
	
	@Test
	public void testDeleteTaskScheduled() throws Exception {
		
		String uri = "/TaskScheduled/deleteTaskScheduled?idTaskScheduled=1";
		boolean res = true;
		
		when(taskScheduledService.deleteTaskScheduled(1)).thenReturn(res);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(res));
		
	}
	
	@Test
	public void testShowTaskScheduled() throws Exception {
		
		String uri = "/TaskScheduled/showTaskScheduled?idScheduling=1";
		List<TaskScheduledDTO> tasks = new ArrayList<TaskScheduledDTO>();
		tasks.add(new TaskScheduledDTO(1,1,false,"prova",1,null));
		tasks.add(new TaskScheduledDTO(1,1,false,"prova",1,null));
		tasks.add(new TaskScheduledDTO(1,1,false,"prova",1,null));
		tasks.add(new TaskScheduledDTO(1,1,false,"prova",1,null));
		
		when(taskScheduledService.getTaskScheduling(1)).thenReturn(tasks);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(tasks));
		
	}
	
	@Test
	public void testShowOperationScheduling() throws Exception {
		
		String uri = "/TaskScheduled/showOperationScheduling?idScheduling=1";
		List<OperationSchedulingDTO> osDTO = new ArrayList<OperationSchedulingDTO>();
		osDTO.add(new OperationSchedulingDTO(1,1,1,1));
		osDTO.add(new OperationSchedulingDTO(1,1,1,1));
		osDTO.add(new OperationSchedulingDTO(1,1,1,1));
		osDTO.add(new OperationSchedulingDTO(1,1,1,1));
		
		when(taskScheduledService.getOperationScheduling(1)).thenReturn(osDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(osDTO));
		
	}
	
	@Test //non so come testare dato che richiede due input
	public void testInsertOutput() {
		
	}
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
