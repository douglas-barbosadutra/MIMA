package com.pCarpet.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pCarpet.converter.MachineConverter;
import com.pCarpet.converter.UserConverter;
import com.pCarpet.dao.MachineDAO;
import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.MachineDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MachineDAO machineDAO;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		userDAO.deleteAll();
	}
	
	//non presente formalmente in UserDAO
	@Test
	public void testInsertUser() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		int userInsertTestId = userDAO.getLastInsertId();
		User user = userDAO.findUserByUsername(userDTO.getUsername());
		userDAO.deleteById(userInsertTestId);
		Assert.assertNotNull(user);
	}
	
	//non presente formalmente in UserDAO
	@Test
	public void testDeleteUser() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		int userInsertTestId = userDAO.getLastInsertId();
		userDAO.deleteById(userInsertTestId);
		Assert.assertNull(userDAO.findUserById(userInsertTestId));
	}
	
	@Test
	public void testFindUserByUsernameAndPassword() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		int userInsertTestId = userDAO.getLastInsertId();
		User user = userDAO.findUserByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword());
		userDAO.deleteById(userInsertTestId);
		Assert.assertTrue(user.getId() == userInsertTestId);
	}
	
	@Test
	public void testFindUserById() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		int userInsertTestId = userDAO.getLastInsertId();
		User user = userDAO.findUserById(userInsertTestId);
		userDAO.deleteById(userInsertTestId);
		Assert.assertTrue(user.getId() == userInsertTestId);
	}

	
	@Test
	public void testFindUserByUsername() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		int userInsertTestId = userDAO.getLastInsertId();
		User user = userDAO.findUserByUsername(userDTO.getUsername());
		userDAO.deleteById(userInsertTestId);
		Assert.assertTrue(user.getId() == userInsertTestId);
	}
	
	@Test
	public void testfindUserByMachines() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDAO.save(UserConverter.toEntity(userDTO));
		int userInsertTestId = userDAO.getLastInsertId();
		MachineDTO machineDTO = new MachineDTO(0,"nome","modello",userInsertTestId);
		Machine machine = MachineConverter.convertToEntity(machineDTO);
		machineDAO.saveAndFlush(machine);
		int machineInsertTestId = machineDAO.getLastInsertId();
		machine.setId(machineInsertTestId);
		User user = userDAO.findUserByMachines(machine);
		Assert.assertTrue(user.getId() == userInsertTestId);
	}
	
	@Test
	public void testFindAll() {
		User user = new User(0,"name","cognome","email@email.it","3212123",0,"username","password", null, null, null, null);
		User user2 = new User(0,"name","cognome","email@email.it","3212123",0,"username","password", null, null, null, null);
		User user3 = new User(0,"name","cognome","email@email.it","3212123",0,"username","password", null, null, null, null);
		userDAO.save(user);
		int userInsertTestId = userDAO.getLastInsertId();
		userDAO.save(user2);
		int userInsertTestId2 = userDAO.getLastInsertId();
		userDAO.save(user3);
		int userInsertTestId3 = userDAO.getLastInsertId();
		List<User> listaUtenti = userDAO.findAll();
		userDAO.deleteById(userInsertTestId);
		userDAO.deleteById(userInsertTestId2);
		userDAO.deleteById(userInsertTestId3);
		assertEquals(listaUtenti.size(), 3);
	}
}
