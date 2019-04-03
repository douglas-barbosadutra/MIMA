package com.mima.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.mima.controller.ItemController;
import com.mima.dto.WBSDTO;
import com.mima.dto.InputDTO;
import com.mima.dto.ItemDTO;
import com.mima.services.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ItemService itemService;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testShowNodes() throws Exception {
		String uri = "/Item/showNodes";
		WBSDTO wbsDTO = new WBSDTO(1, "test", 1);
		String inputJson = this.objectToJson(wbsDTO);
		ItemDTO item1 = new ItemDTO(1, "uno", 0, 1, new ArrayList<ItemDTO>());
		ItemDTO item2 = new ItemDTO(2, "due", 1, 1, new ArrayList<ItemDTO>());
		ItemDTO item3 = new ItemDTO(3, "tre", 1, 1, new ArrayList<ItemDTO>());
		item1.getItemChildrenDTO().add(item2);
		item1.getItemChildrenDTO().add(item3);
		when(itemService.getItemByWBS(wbsDTO)).thenReturn(item1);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(content, objectToJson(item1));
	}
	
	@Test
	public void testAddNode() throws Exception {
		String uri = "/Item/addNode";
		ItemDTO item1 = new ItemDTO(1, "uno", 0, 1, new ArrayList<ItemDTO>());
		String inputJson = this.objectToJson(item1);
		when(itemService.insertItem(item1)).thenReturn(item1);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(content, objectToJson(item1));
	}
	
	@Test
	public void testRemoveNode() throws Exception {
		String uri = "/Item/removeNode?idItem=1";
		boolean result = true;
		when(itemService.deleteItem(1)).thenReturn(result);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(result));
	}
	
	@Test
	public void testShowItem() throws Exception {
		String uri = "/Item/showItem";
		ItemDTO item1 = new ItemDTO(1, "uno", 0, 1, new ArrayList<ItemDTO>());
		ItemDTO item2 = new ItemDTO(2, "due", 1, 1, new ArrayList<ItemDTO>());
		ItemDTO item3 = new ItemDTO(3, "tre", 1, 1, new ArrayList<ItemDTO>());
		List<ItemDTO> list = new ArrayList<ItemDTO>();
		list.add(item1);	list.add(item2);	list.add(item3);
		when(itemService.getAllItem()).thenReturn(list);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, this.objectToJson(list));
	}
	
	@Test
	public void testInsertInput() throws Exception {
		String uri = "/Item/insertInput";
		InputDTO input = new InputDTO(1, 2);
		boolean result = true;
		String inputJson = this.objectToJson(input);
		when(itemService.insertInput(input.getIdItem(), input.getIdTask())).thenReturn(result);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assert.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(content, objectToJson(result));
	}

	public String objectToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
