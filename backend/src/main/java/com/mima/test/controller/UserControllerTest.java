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
import com.mima.controller.UserController;
import com.mima.dto.MachineDTO;
import com.mima.dto.TaskDTO;
import com.mima.dto.UserDTO;
import com.mima.services.TaskService;
import com.mima.services.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)

public class UserControlleTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertUser()  throws Exception {
		
		String uri = "/User/insertUser";
		UserDTO userDTO = new UserDTO(1,"prova","prova" ,"prova" ,"prova" ,"prova" ,"prova",1);
		String inputJson = this.objectToJson(userDTO);
		
		when(userService.insertUser(userDTO)).thenReturn(userDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(userDTO));
	}
	
	
	@Test
	public void testDeleteUser() throws Exception {
		String uri = "/User/deleteUser?idUser=1";
		boolean result = true;
		when(userService.deleteUser(1)).thenReturn(result);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(result));
	}
	
	@Test
	public void testShowUser() throws Exception {
		
		String uri = "/User/showUser?idTask=1";
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		userDTO.add(new UserDTO(1,"prova","prova" ,"prova" ,"prova" ,"prova" ,"prova",1));
		userDTO.add(new UserDTO(2,"prova","prova" ,"prova" ,"prova" ,"prova" ,"prova",2));

		
		when(userService.getAllUsers()).thenReturn(userDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(userDTO));
	}

	@Test
	public void testUpdateUser()  throws Exception {
		
		String uri = "/User/insertUser";
		UserDTO userDTO = new UserDTO(1,"prova","prova" ,"prova" ,"prova" ,"prova" ,"prova",1);
		String inputJson = this.objectToJson(userDTO);
		
		when(userService.insertUser(userDTO)).thenReturn(userDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(userDTO));
	}
	
	
	@Test
	public void testFindUser()  throws Exception {
		
		String uri = "/User/findUser?idUser=1";
		UserDTO userDTO = new UserDTO(1,"prova","prova" ,"prova" ,"prova" ,"prova" ,"prova",1);
		String inputJson = this.objectToJson(userDTO);
		
		when(userService.findUserById(userDTO.getId())).thenReturn(userDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		         
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(userDTO));
	}
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
