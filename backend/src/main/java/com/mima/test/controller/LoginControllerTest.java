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
import com.pCarpet.controller.LoginController;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.services.LoginService;


@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {
	
	//https://www.tutorialspoint.com/spring_boot/spring_boot_rest_controller_unit_test.htm
	
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private LoginService loginService;

	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testLogin() throws Exception {
		String uri = "/Login/login";
		String inputJson = "{ \"username\":\"username\", \"password\":\"password\" }";
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		
		when(loginService.login(userDTO.getUsername(), userDTO.getPassword())).thenReturn(userDTO);	
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE)
		         .content(inputJson)).andReturn();
		
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
