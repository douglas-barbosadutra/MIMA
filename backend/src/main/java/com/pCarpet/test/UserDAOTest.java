package com.pCarpet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pCarpet.converter.UserConverter;
import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.UserDTO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {
	
	@Autowired
	private UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertUser() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		System.out.println(userDAO.getLastInsertId());
		UserDTO u = UserConverter.toDTO(userDAO.findUserByUsernameAndPassword("username", "password"));
		System.out.println(u);
	}
}
