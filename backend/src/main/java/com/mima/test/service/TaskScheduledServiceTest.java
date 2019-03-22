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

import com.pCarpet.converter.TaskScheduledConverter;
import com.pCarpet.dao.TaskScheduledDAO;
import com.pCarpet.dto.TaskScheduledDTO;
import com.pCarpet.services.TaskScheduledService;

public class TaskScheduledServiceTest {

	@Mock
	private TaskScheduledDAO taskScheduledDAO;
	
	@InjectMocks
	private TaskScheduledService taskScheduledService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertTaskScheduledRelations() {
		
		
	}
	
	@Test
	public void testInsertTaskScheduled() {
		TaskScheduledDTO task = new TaskScheduledDTO(1, 1, false, null, 1, new ArrayList<TaskScheduledDTO>());
		when(taskScheduledDAO.saveAndFlush(TaskScheduledConverter.convertToEntity(task))).thenReturn(TaskScheduledConverter.convertToEntity(task));
		assertThat(taskScheduledService.insertTaskScheduled(task), is (task));
	}
	
	@Test
	public void testDeleteTaskScheduled() {
		
	}
	
	@Test
	public void testGetTaskScheduledRoot() {
		
	}
	
	@Test
	public void testGetOperationScheduling() {
		
	}
	
	@Test
	public void testInsertOutput() {
		
	}
}
