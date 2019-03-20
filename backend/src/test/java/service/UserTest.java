package service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pCarpet.converter.UserConverter;
import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;
import com.pCarpet.services.UserService;

public class UserTest {
	
	@Mock
	private UserDAO userDAO;
	@Mock
	private UserConverter userConverter;
	
	@InjectMocks
	private UserService userService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testInsertUser() {
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		when(userDAO.saveAndFlush(UserConverter.toEntity(userDTO))).thenReturn(UserConverter.toEntity(userDTO));
		assertThat(userService.insertUser(userDTO), is (notNullValue()));
	}
	
	@Test
	public void testFindUserById() {
		UserDTO userDTO = new UserDTO(3,"username","password","name","cognome","email@email.it","3212123", 1);
		when(userDAO.findUserById(1)).thenReturn(UserConverter.toEntity(userDTO));		
		UserDTO userDTO2 = userService.findUserById(1);
		assertEquals(userDTO.getId(), userDTO2.getId());
		assertEquals(userDTO.getUsername(), userDTO2.getUsername());
		assertEquals(userDTO.getPassword(), userDTO2.getPassword());
	}
	
	@Test
	public void testGetUserByUsername() {
		UserDTO userDTO = new UserDTO(3,"username","password","name","cognome","email@email.it","3212123", 1);
		when(userDAO.findUserByUsername("prova")).thenReturn(UserConverter.toEntity(userDTO));		
		UserDTO userDTO2 = userService.getUserByUsername("prova");
		assertEquals(userDTO.getId(), userDTO2.getId());
		assertEquals(userDTO.getUsername(), userDTO2.getUsername());
		assertEquals(userDTO.getPassword(), userDTO2.getPassword());
	}
	
	@Test
	public void testGetAllUsers() {
		List<User> listaUtenti = new ArrayList<User>();
		User user = new User(1,"name","cognome","email@email.it","3212123",0,"username","password", null, null, null, null);
		User user2 = new User(2,"name","cognome","email@email.it","3212123",0,"username","password", null, null, null, null);
		User user3 = new User(3,"name","cognome","email@email.it","3212123",0,"username","password", null, null, null, null);
		listaUtenti.add(user);
		listaUtenti.add(user2);
		listaUtenti.add(user3);
		when(userDAO.findAll()).thenReturn(listaUtenti);
		List<UserDTO> listaUtenti1 = userService.getAllUsers();
		assertEquals(listaUtenti1.size(), listaUtenti.size());
	}
	
	@Test
	public void testDeleteUser() {

	}

}
