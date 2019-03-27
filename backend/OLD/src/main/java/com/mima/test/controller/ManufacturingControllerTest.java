package com.mima.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
import com.mima.controller.ManufacturingController;
import com.mima.dto.TimeDTO;
import com.mima.services.TimeService;

@RunWith(SpringRunner.class)
@WebMvcTest(ManufacturingController.class)
public class ManufacturingControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TimeService timeService;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testShowTime() throws Exception {
		String uri = "/Manufacturing/showTime?idTask=1";
		List<TimeDTO> timesDTO = new ArrayList<TimeDTO>();
		timesDTO.add(new TimeDTO("prova","prova",1,1,"prova"));
		timesDTO.add(new TimeDTO("prova","prova",1,1,"prova"));
		timesDTO.add(new TimeDTO("prova","prova",1,1,"prova"));
		timesDTO.add(new TimeDTO("prova","prova",1,1,"prova"));
		
		when(timeService.getAllTempi(1)).thenReturn(timesDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(timesDTO));
	}
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
