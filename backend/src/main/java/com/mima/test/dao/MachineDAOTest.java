package com.mima.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mima.converter.MachineConverter;
import com.mima.converter.UserConverter;
import com.mima.dao.MachineDAO;
import com.mima.dao.UserDAO;
import com.mima.dto.MachineDTO;
import com.mima.dto.UserDTO;
import com.mima.model.Machine;
import com.mima.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MachineDAOTest {

	@Autowired
	private MachineDAO machineDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	private int idUser;
	
	@Before
	public void setUp() throws Exception {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDTO = UserConverter.toDTO(userDAO.save(UserConverter.toEntity(userDTO)));
		idUser = userDAO.getLastInsertId();
	}

	@After
	public void tearDown() throws Exception {
		machineDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testFindAllByUser() {
		User user = userDAO.findUserById(idUser);
		List<Machine> list = new ArrayList<Machine>();
		MachineDTO machine1 = new MachineDTO(0, "uno", "uno", idUser);
		MachineDTO machine2 = new MachineDTO(0, "ahi", "test", idUser);
		machine1 = MachineConverter.convertToDto(machineDAO.saveAndFlush(MachineConverter.convertToEntity(machine1)));
		machine2 = MachineConverter.convertToDto(machineDAO.saveAndFlush(MachineConverter.convertToEntity(machine2)));
		list = machineDAO.findAllByUser(user);
		assertEquals(list.size(), 2);
	}
}
