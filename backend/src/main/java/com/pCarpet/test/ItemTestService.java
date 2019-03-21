package com.pCarpet.test;

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

import com.pCarpet.converter.ItemConverter;
import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.ItemDAO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.WBSDTO;
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
	public void testInsertItem() {
		ItemDTO itemDTO = new ItemDTO(1, "test", 0, 1, new ArrayList<ItemDTO>());
		when(itemDAO.insertItem(itemDTO.getName(), null, itemDTO.getIdWBS())).thenReturn(1);
		assertThat(itemService.insertItem(itemDTO), is (itemDTO));
	}
	
	@Test
	public void testDeleteItem() {
		
	}
	
	@Test
	public void testGetItemByWBS() {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		ItemDTO father = new ItemDTO(1, "test", 0, 1, new ArrayList<ItemDTO>());
		ItemDTO child = new ItemDTO(1, "test", 1, 1, new ArrayList<ItemDTO>());
		list.add(father);
		list.add(child);
		WBSDTO wbs = new WBSDTO(1, "prova", 1);
		when(itemDAO.findAllByWbs(WBSConverter.convertToEntity(wbs))).thenReturn(ItemConverter.toListEntity(list));
		assertThat(itemService.getItemByWBS(wbs), is (father));
	}
	
	@Test
	public void testGetAllItem() {
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		ItemDTO father = new ItemDTO(1, "test", 0, 1, new ArrayList<ItemDTO>());
		ItemDTO child = new ItemDTO(1, "test", 1, 1, new ArrayList<ItemDTO>());
		list.add(father);
		list.add(child);
		when(itemDAO.findAll()).thenReturn(ItemConverter.toListEntity(list));
		assertThat(itemService.getAllItem(), is(list));
	}
	
	@Test
	public void testInsertInput() {
		
	}
}
