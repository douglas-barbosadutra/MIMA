package com.pCarpet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pCarpet.dao.ItemDAO;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemDAOTest {

	@Autowired
	ItemDAO itemDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		itemDAO.deleteAll();
	}
}
