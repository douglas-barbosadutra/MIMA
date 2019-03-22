package com.pCarpet.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pCarpet.converter.EmployeeConverter;
import com.pCarpet.converter.UserConverter;
import com.pCarpet.dao.EmployeeDAO;
import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.Employee;
import com.pCarpet.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		employeeDAO.deleteAll();
		userDAO.deleteAll();
	}
	
	@Test
	public void testInsertEmployee() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		User user = userDAO.saveAndFlush(UserConverter.toEntity(userDTO));
		
		EmployeeDTO employeeDTO = new EmployeeDTO(1,UserConverter.toDTO(user),0,user.getId());
		int idEmployeeInsert = employeeDAO.saveAndFlush(EmployeeConverter.convertToEntity(employeeDTO)).getId();
		
		Optional<Employee> employee = employeeDAO.findById(idEmployeeInsert);
		Assert.assertTrue(employee.isPresent());
	}
	
	@Test
	public void testDeleteEmployee() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		User user = userDAO.saveAndFlush(UserConverter.toEntity(userDTO));
		
		EmployeeDTO employeeDTO = new EmployeeDTO(1,UserConverter.toDTO(user),0,user.getId());
		int idEmployeeInsert = employeeDAO.saveAndFlush(EmployeeConverter.convertToEntity(employeeDTO)).getId();
		
		employeeDAO.deleteById(idEmployeeInsert);
		Optional<Employee> employee = employeeDAO.findById(idEmployeeInsert);
		Assert.assertTrue(!employee.isPresent());
	}
	
	@Test
	public void testFindEmployeeByUser() {
		

		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		User user = userDAO.saveAndFlush(UserConverter.toEntity(userDTO));
		
		EmployeeDTO employeeDTO = new EmployeeDTO(1,UserConverter.toDTO(user),0,user.getId());
		Employee employee = employeeDAO.saveAndFlush(EmployeeConverter.convertToEntity(employeeDTO));
		
		Employee employeeFind = employeeDAO.findEmployeeByUser(user);
		Assert.assertTrue(employee.equals(employeeFind));
	}
	
	@Test //non funziona
	public void testFindAllByBusinessOwner() {
		
		UserDTO userDTO = new UserDTO(0,"username","password","name","cognome","email@email.it","3212123", 0);
		User user = userDAO.saveAndFlush(UserConverter.toEntity(userDTO));
		
		UserDTO businessOwnerDTO = new UserDTO(1,"username","password","name","cognome","email@email.it","3212123", 0);
		User businessOwner = userDAO.saveAndFlush(UserConverter.toEntity(businessOwnerDTO));
		
		List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();
		employeesDTO.add(new EmployeeDTO(1,UserConverter.toDTO(user),0,businessOwner.getId()));
		employeesDTO.add(new EmployeeDTO(1,UserConverter.toDTO(user),0,businessOwner.getId()));
		employeesDTO.add(new EmployeeDTO(1,UserConverter.toDTO(user),0,businessOwner.getId()));
		employeesDTO.add(new EmployeeDTO(1,UserConverter.toDTO(user),0,businessOwner.getId()));
		for(EmployeeDTO employee: employeesDTO)
			employeeDAO.saveAndFlush(EmployeeConverter.convertToEntity(employee));
		
		List<Employee> employees = employeeDAO.findAllByBusinessOwner(businessOwner);
		Assert.assertTrue(employeesDTO.size() == employees.size());
	}

}
