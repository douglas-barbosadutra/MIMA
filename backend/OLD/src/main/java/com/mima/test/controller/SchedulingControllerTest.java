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
import com.mima.controller.SchedulingController;
import com.mima.dto.SchedulingDTO;
import com.mima.services.SchedulingService;

@RunWith(SpringRunner.class)
@WebMvcTest(SchedulingController.class)
public class SchedulingControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SchedulingService schedulingService;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testInsertScheduling() throws Exception {
		String uri = "/Scheduling/insertScheduling";
		SchedulingDTO scheduling = new SchedulingDTO(1, "nome", "datainizio", "datafine", 1);
		String inputJson = this.objectToJson(scheduling);
		when(schedulingService.insertScheduling(scheduling)).thenReturn(scheduling);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(content, objectToJson(scheduling));
	}

	@Test
	public void testDeleteScheduling() throws Exception {
		String uri = "/Scheduling/deleteScheduling?idScheduling=1";
		boolean result = true;
		when(schedulingService.deleteScheduling(1)).thenReturn(result);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(result));
	}

	@Test
	public void testShowScheduling() throws Exception {
		String uri = "/Scheduling/showScheduling?idMachine=1";
		SchedulingDTO scheduling1 = new SchedulingDTO(1, "uno", "datainizio", "datafine", 1);
		SchedulingDTO scheduling2 = new SchedulingDTO(2, "due", "datainizio", "datafine", 1);
		SchedulingDTO scheduling3 = new SchedulingDTO(2, "due", "datainizio", "datafine", 1);
		List<SchedulingDTO> list = new ArrayList<SchedulingDTO>();
		list.add(scheduling1);
		list.add(scheduling2);
		list.add(scheduling3);
		when(schedulingService.getAllScheduling(1)).thenReturn(list);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(list));
	}

	@Test
	public void testUpdateScheduling() throws Exception {
		String uri = "/Scheduling/updateScheduling";
		SchedulingDTO scheduling = new SchedulingDTO(1, "nome", "datainizio", "datafine", 1);
		String inputJson = this.objectToJson(scheduling);
		when(schedulingService.insertScheduling(scheduling)).thenReturn(scheduling);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(content, objectToJson(scheduling));
	}

	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
