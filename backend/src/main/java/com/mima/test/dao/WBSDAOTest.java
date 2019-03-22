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

import com.mima.converter.UserConverter;
import com.mima.converter.WBSConverter;
import com.mima.dao.UserDAO;
import com.mima.dao.WBSDAO;
import com.mima.dto.UserDTO;
import com.mima.dto.WBSDTO;
import com.mima.model.User;
import com.mima.model.WBS;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WBSDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	private int idUser;
	
	@Autowired
	private WBSDAO wbsDAO;
	
	@Before
	public void setUp() throws Exception {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		userDTO = UserConverter.toDTO(userDAO.save(UserConverter.toEntity(userDTO)));
		idUser = userDAO.getLastInsertId();
	}
	
	@After
	public void tearDown() throws Exception {
		wbsDAO.deleteAll();
		userDAO.deleteAll();
	}

	@Test
	public void testFindAllByUser() {
		User user = userDAO.findUserById(idUser);
		List<WBS> list = new ArrayList<WBS>();
		WBSDTO wbs1 = new WBSDTO(0, "name", user.getId());
		WBSDTO wbs2 = new WBSDTO(0, "name", user.getId());
		wbs1 = WBSConverter.convertToDto(wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbs1)));
		wbs2 = WBSConverter.convertToDto(wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbs2)));
		list = wbsDAO.findAllByUser(user);
		assertEquals(list.size(), 2);
	}
}
