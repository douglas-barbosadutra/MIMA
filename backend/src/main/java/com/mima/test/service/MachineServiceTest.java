package com.pCarpet.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pCarpet.converter.MachineConverter;
import com.pCarpet.dao.MachineDAO;
import com.pCarpet.dto.MachineDTO;
import com.pCarpet.model.Machine;
import com.pCarpet.model.User;
import com.pCarpet.services.MachineService;

public class MachineServiceTest {
	
	@Mock
	private MachineDAO machineDAO;
	
	@InjectMocks
	private MachineService machineService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertMachine() {
		
		MachineDTO machineDTO = new MachineDTO(0,"prova","prova",2);
		Machine machine = MachineConverter.convertToEntity(machineDTO);
		
		when(machineDAO.saveAndFlush(machine)).thenReturn(machine);
		assertThat(machineService.insertMachine(machineDTO), is (machineDTO));
	}
	
	@Test
	public void testGetAllMachinesByIdUser() {
		User user = new User();
		user.setId(2);
		
		List<MachineDTO> machinesDTO = new ArrayList<MachineDTO>();
		machinesDTO.add(new MachineDTO(0,"prova","prova",2));
		machinesDTO.add(new MachineDTO(0,"prova","prova",2));
		machinesDTO.add(new MachineDTO(0,"prova","prova",2));
		machinesDTO.add(new MachineDTO(0,"prova","prova",2));
		List<Machine> machines = MachineConverter.toListEntity(machinesDTO);
		
		when(machineDAO.findAllByUser(user)).thenReturn(machines);
		assertThat(machineService.getAllMachinesByIdUser(user.getId()), is (machinesDTO));
	}
	
	@Test
	public void testDeleteMachine() {
		
	}

}
