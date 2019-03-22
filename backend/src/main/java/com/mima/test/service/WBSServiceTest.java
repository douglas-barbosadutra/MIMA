package com.mima.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mima.converter.UserConverter;
import com.mima.converter.WBSConverter;
import com.mima.dao.WBSDAO;
import com.mima.dto.ItemDTO;
import com.mima.dto.UserDTO;
import com.mima.dto.WBSDTO;
import com.mima.model.WBS;
import com.mima.services.ItemService;
import com.mima.services.WBSService;


public class WBSServiceTest {
	
	@Mock
	private WBSDAO wbsDao;
		
	@Mock
	private ItemService itemService;
	
	@InjectMocks
	private WBSService wbsService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertWBS() {
		WBSDTO wbs = new WBSDTO(0, "prova", 1);
		ItemDTO item = new ItemDTO(0, "test", 0, 1, null);
		when(wbsDao.saveAndFlush(WBSConverter.convertToEntity(wbs))).thenReturn(WBSConverter.convertToEntity(wbs));
		doReturn(item).when(itemService).insertItem(item);						//questa sintassi è equivalente a quella della riga precedente
		assertThat(wbsService.insertWBS(wbs), is (wbs));
	}
	
	@Test
	public void testShowWBS() {
		UserDTO user = new UserDTO();
		user.setId(1);
		List<WBS> list = new ArrayList<WBS>();
		when(wbsDao.findAllByUser(UserConverter.toEntity(user))).thenReturn(list);
		assertThat(wbsService.showWBS(user.getId()), is (list) );
	}
	
	@Test
	public void testDeleteWBS() {
		
	}
}
