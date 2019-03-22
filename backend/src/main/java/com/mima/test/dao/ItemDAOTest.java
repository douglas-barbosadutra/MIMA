package com.mima.test.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mima.converter.ItemConverter;
import com.mima.converter.UserConverter;
import com.mima.converter.WBSConverter;
import com.mima.dao.ItemDAO;
import com.mima.dao.UserDAO;
import com.mima.dao.WBSDAO;
import com.mima.dto.ItemDTO;
import com.mima.dto.UserDTO;
import com.mima.dto.WBSDTO;
import com.mima.model.Item;
import com.mima.model.WBS;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private WBSDAO wbsDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
		itemDAO.deleteAll();
		wbsDAO.deleteAll();
		userDAO.deleteAll();
		
	}
	
	/*@Test
	public void testInsertItem() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(0,"prova",idUserInsert);
		int idWbsInsert = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO)).getId();
		
		ItemDTO itemDTO = new ItemDTO(1,"prova",0,idWbsInsert,null);
		int test = itemDAO.insertItem(itemDTO.getName(), itemDTO.getIdFather(), idWbsInsert);
		
		Assert.assertTrue(test==1);
	}*/
	
	/*@Test
	public void testDeleteItem() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(0,"prova",idUserInsert);
		int idWbsInsert = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO)).getId();
		
		ItemDTO itemDTO = new ItemDTO(1,"prova",0,idWbsInsert,null);
		int idItemInsert = itemDAO.saveAndFlush(ItemConverter.convertToEntity(itemDTO)).getId();
		
		itemDAO.deleteById(idItemInsert);
		Optional<Item> item = itemDAO.findById(idItemInsert);
		
		itemDAO.deleteAll();
		wbsDAO.deleteAll();
		userDAO.deleteAll();
		
		Assert.assertTrue(!item.isPresent());
	}*/
	
	@Test
	public void testFindAllByWbs() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(0,"prova",idUserInsert);
		WBS wbs = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO));
		
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs.getId(),null));
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs.getId(),null));
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs.getId(),null));
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs.getId(),null));
		for(ItemDTO item: itemsDTO)
			itemDAO.insertItem(item.getName(),item.getIdFather(),wbs.getId());
		
		List<Item> items = itemDAO.findAllByWbs(wbs);
		
		System.out.println("itemsSize: "+items.size());
		System.out.println("itemsDTOSize: "+itemsDTO.size());
		
		itemDAO.deleteAll();
		wbsDAO.deleteAll();
		userDAO.deleteAll();
		
		Assert.assertTrue(itemsDTO.size() == items.size());
	}
	
	/*@Test
	public void testFindAll() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		int idUserInsert = userDAO.saveAndFlush(UserConverter.toEntity(userDTO)).getId();
		
		WBSDTO wbsDTO = new WBSDTO(0,"prova",idUserInsert);
		WBS wbs = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbsDTO));
		
		WBSDTO wbs2DTO = new WBSDTO(1,"prova2",idUserInsert);
		WBS wbs2 = wbsDAO.saveAndFlush(WBSConverter.convertToEntity(wbs2DTO));
		
		List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs.getId(),null));
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs.getId(),null));
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs2.getId(),null));
		itemsDTO.add(new ItemDTO(1,"prova",0,wbs2.getId(),null));
		for(ItemDTO item: itemsDTO)
			itemDAO.saveAndFlush(ItemConverter.convertToEntity(item));
		
		List<Item> items = itemDAO.findAll();
		boolean test = true;
		
		if(itemsDTO.size() != items.size())
			test = false;
		
		else {
			
			for(int i=0; i<itemsDTO.size(); i++) {
				if(!itemsDTO.get(i).equals(ItemConverter.convertToDto(items.get(i)))) {
					test = false;
					break;
				}
			}
		}
		Assert.assertTrue(test);
	}*/
	
}