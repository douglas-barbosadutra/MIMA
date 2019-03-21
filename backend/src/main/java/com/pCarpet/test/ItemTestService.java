package com.pCarpet.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pCarpet.converter.ItemConverter;
import com.pCarpet.dao.ItemDAO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.services.ItemService;

public class ItemTestService {

	@Mock
	private ItemDAO itemDAO;
	
	@InjectMocks
	private ItemService itemService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testGetItemById() {
		ItemDTO itemDTO = new ItemDTO(1, "test", 1, 1, null);
		itemDTO.setItemChildrenDTO(new ArrayList<ItemDTO>());
		when(itemDAO.findItemById(itemDTO.getId())).thenReturn(ItemConverter.convertToEntity(itemDTO));
		assertThat(itemService.getItemById(1), is (itemDTO));
	}
	
	@Test
	public void testGetItemByWBS() {
		
	}
}
