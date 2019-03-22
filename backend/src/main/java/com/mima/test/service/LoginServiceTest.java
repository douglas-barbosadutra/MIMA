package com.mima.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mima.converter.UserConverter;
import com.mima.dao.UserDAO;
import com.mima.dto.UserDTO;
import com.mima.model.User;
import com.mima.services.LoginService;

public class LoginServiceTest {
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private LoginService loginService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testLogin() {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("user");
		userDTO.setPassword("user");
		User user = UserConverter.toEntity(userDTO);
		
		when(userDAO.findUserByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword())).thenReturn(user);
		assertThat(loginService.login(userDTO.getUsername(), userDTO.getPassword()), is (userDTO));
	}

}
