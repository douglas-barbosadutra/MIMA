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
import com.mima.controller.WBSController;
import com.mima.dto.WBSDTO;
import com.mima.services.WBSService;

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
	public void testShowWbs() throws Exception {
		String uri = "/WBS/showWbs?idUser=1";
		List<WBSDTO> list = new ArrayList<WBSDTO>();
		WBSDTO elem1 = new WBSDTO(1, "primo elem", 1);
		WBSDTO elem2 = new WBSDTO(2, "secondo elem", 2);
		WBSDTO elem3 = new WBSDTO(3, "terzo elem", 2);
		list.add(elem1); list.add(elem2); list.add(elem3);
		when(wbsService.showWBS(1)).thenReturn(list);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(list));
	}
	/*
	@Test
	public void testDeleteWbs() throws Exception {
		String uri = "/WBS/deleteWbs?idUser=1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		boolean result = true;
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(result));
	}*/
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
