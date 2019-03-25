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
import com.mima.controller.EmployeeController;
import com.mima.dto.EmployeeDTO;
import com.mima.dto.UserDTO;
import com.mima.services.EmployeeService;
import com.mima.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertEmployee() throws Exception {
		
		String uri = "/Employee/insertEmployee";
		UserDTO user = new UserDTO(1,"username","password","name","surname","email","phone",0);
		UserDTO businessOwner = new UserDTO(2,"username","password","name","surname","email","phone",0);
		EmployeeDTO employeeDTO = new EmployeeDTO(1,user,0,businessOwner.getId());
		String inputJson = this.objectToJson(employeeDTO);
		
		when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
		when(employeeService.insertEmployee(employeeDTO)).thenReturn(employeeDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(employeeDTO));
		
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		
		String uri = "/Employee/deleteEmployee?idUser=1";
		boolean res = true;
		
		when(userService.deleteUser(1)).thenReturn(res);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(res));
		
	}
	
	@Test 
	public void testShowEmployee() throws Exception {
		
		String uri = "/Employee/showEmployee?idBusinessOwner=2";
		UserDTO user = new UserDTO(1,"username","password","name","surname","email","phone",0);
		
		List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();
		employeesDTO.add(new EmployeeDTO(1,user,0,2));
		employeesDTO.add(new EmployeeDTO(1,user,0,2));
		employeesDTO.add(new EmployeeDTO(1,user,0,2));
		employeesDTO.add(new EmployeeDTO(1,user,0,2));
		
		when(employeeService.getEmployeeByIdBusinessOwner(2)).thenReturn(employeesDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(employeesDTO));
		
	}
	
	@Test
	public void testAssignTask() throws Exception {
		
		String uri = "/Employee/assignTask";
		UserDTO user = new UserDTO(1,"username","password","name","surname","email","phone",0);
		EmployeeDTO employeeDTO = new EmployeeDTO(1,user,1,2);
		String inputJson = this.objectToJson(employeeDTO);
		
		when(employeeService.insertEmployee(employeeDTO)).thenReturn(employeeDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
	    Assert.assertEquals(200, status);
	    
	    String content = mvcResult.getResponse().getContentAsString();
	    Assert.assertEquals(content, objectToJson(employeeDTO));
		
	}
	
	@Test
	public void testFindEmployee() throws Exception {
		
		String uri = "/Employee/findEmployee?idUser=1";
		UserDTO user = new UserDTO(1,"username","password","name","surname","email","phone",0);
		EmployeeDTO employeeDTO = new EmployeeDTO(1,user,1,2);
		
		when(employeeService.getEmployeeByUser(1)).thenReturn(employeeDTO);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(employeeDTO));
	}
	
	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
