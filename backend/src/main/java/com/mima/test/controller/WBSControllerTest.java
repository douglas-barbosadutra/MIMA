package com.pCarpet.test;

import static org.mockito.Mockito.when;

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
import com.pCarpet.controller.WBSController;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.services.WBSService;

@RunWith(SpringRunner.class)
@WebMvcTest(WBSController.class)
public class WBSControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private WBSService wbsService;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertWBS() throws Exception {
		String uri = "/WBS/insertWbs";
		WBSDTO wbsDTO = new WBSDTO(1, "test", 1);
		String inputJson = this.objectToJson(wbsDTO);
		
		System.out.println(inputJson);
		
		when(wbsService.insertWBS(wbsDTO)).thenReturn(wbsDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(wbsDTO));
	}
	
	@Test
	public void testShowWbs() {
		String uri = "/WBS/showWbs?idUser=1";
		//MvcResult mvcResult = 
	}
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
