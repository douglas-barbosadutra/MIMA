package com.pCarpet.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
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

import com.pCarpet.converter.EmployeeConverter;
import com.pCarpet.converter.UserConverter;
import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.EmployeeDAO;
import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.Employee;
import com.pCarpet.model.User;
import com.pCarpet.model.WBS;
import com.pCarpet.services.EmployeeService;

public class EmployeeTest {
	
	@Mock
	private EmployeeDAO employeeDAO;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testInsertEmployee(){
	UserDTO user=new UserDTO(1,"test","test","test","test","test","test",0 );
	EmployeeDTO employee=new EmployeeDTO(1,user,1,2);
	when(employeeDAO.saveAndFlush(EmployeeConverter.convertToEntity(employee))).thenReturn(EmployeeConverter.convertToEntity(employee));
	//doReturn(employee).when(employeeService).insertEmployee(employee);
	assertThat(employeeService.insertEmployee(employee), is (employee));}
	
	/*
	@Test
	public void testDeleteWBS() {
	
	}*/
	
	/*
	@Test
	public void getEmployeeByIdBusinessOwner() {
		UserDTO user=new UserDTO(2,"test","test","test","test","test","test",2 );
		EmployeeDTO employee=new EmployeeDTO(1,user,1,2);
		UserDTO businessOwner=new UserDTO(1,"test","test","test","test","test","test",0);
		List<EmployeeDTO> list=new ArrayList<EmployeeDTO>();
		list.add(employee);
		when(employeeDAO.findAllByBusinessOwner(UserConverter.toEntity(businessOwner))).thenReturn(EmployeeConverter.toListEntity(list));
		List<EmployeeDTO> employeeList  = employeeService.getEmployeeByIdBusinessOwner(businessOwner.getId());
		assertEquals(employeeList.size(), list.size());
	} */
	
	@Test
	public void getEmployeeByUser() {
		UserDTO user=new UserDTO(2,"test","test","test","test","test","test",2 );
		user.setId(1);
		EmployeeDTO employee=new EmployeeDTO(1,user,1,2);
		when(employeeDAO.saveAndFlush(EmployeeConverter.convertToEntity(employee))).thenReturn(EmployeeConverter.convertToEntity(employee));
		assertThat(employeeService.getEmployeeByUser(user.getId()), is (user));
		
		
	}
	
}


	
