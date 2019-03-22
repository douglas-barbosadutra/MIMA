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
import com.mima.controller.InstructionController;
import com.mima.dto.InstructionDTO;
import com.mima.services.InstructionService;

@RunWith(SpringRunner.class)
@WebMvcTest(InstructionController.class)
public class InstructionControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private InstructionService instructionService;

	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertInstruction() throws Exception {
		String uri = "/Instruction/insertInstruction";
		InstructionDTO instructionDTO = new InstructionDTO(1,0,"prova","prova",1);
		String inputJson = this.objectToJson(instructionDTO);
		
		when(instructionService.insertIstruzione(instructionDTO)).thenReturn(instructionDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(instructionDTO));
	}
	
	@Test
	public void testShowInstruction() throws Exception {
		String uri = "/Instruction/showInstruction?idTask=1";
		List<InstructionDTO> instructionsDTO = new ArrayList<InstructionDTO>();
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		instructionsDTO.add(new InstructionDTO(1,0,"prova","prova",1));
		
		when(instructionService.getAllIstruzioniByIdTask(1)).thenReturn(instructionsDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(instructionsDTO));
	}
	
	@Test
	public void testDeleteInstruction() throws Exception {
		String uri = "/Instruction/deleteInstruction?idInstruction=1";
		boolean result = true;
		
		when(instructionService.deleteIstruzione(1)).thenReturn(result);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(result));
	}
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
