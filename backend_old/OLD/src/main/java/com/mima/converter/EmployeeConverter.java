package com.mima.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.dto.EmployeeDTO;
import com.mima.dto.UserDTO;
import com.mima.model.Employee;
import com.mima.model.Task;
import com.mima.model.User;

public class EmployeeConverter {

	public static EmployeeDTO convertToDto(Employee employee) {
		EmployeeDTO employeeDTO = null;
		if(employee != null) {
			employeeDTO = new EmployeeDTO();
			employeeDTO.setId(employee.getId());
			employeeDTO.setUser(UserConverter.toDTO(employee.getUser()));
			if(employee.getTask() == null)
				employeeDTO.setIdTask(0);
			else	
				employeeDTO.setIdTask(employee.getTask().getId());
			employeeDTO.setIdBusinessOwner(employee.getBusinessOwner().getId());
		}
		return employeeDTO;
	}
	
	public static Employee convertToEntity(EmployeeDTO employeeDTO) {
		Employee employee = null;
		if(employeeDTO != null) {
			employee = new Employee();
			employee.setId(employeeDTO.getId());
			employee.setUser(UserConverter.toEntity(employeeDTO.getUser()));
			User businessOwner = new User();
			businessOwner.setId(employeeDTO.getIdBusinessOwner());
			employee.setBusinessOwner(businessOwner);
			if(employeeDTO.getIdTask() != 0) {
				Task task = new Task();
				task.setId(employeeDTO.getIdTask());
				employee.setTask(task);
			}
		}
		return employee;
	}
	
	public static List<EmployeeDTO> toListDTO(List<Employee> list) {
		List<EmployeeDTO> listEmployeeDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Employee employee: list) {
				listEmployeeDTO.add(EmployeeConverter.convertToDto(employee));
			}
		}
		return listEmployeeDTO;
	}
	
	public static List<Employee> toListEntity(List<EmployeeDTO> list) {
		List<Employee> listEmployee = new ArrayList<>();
		if (!list.isEmpty()) {
			for (EmployeeDTO employee: list) {
				listEmployee.add(EmployeeConverter.convertToEntity(employee));
			}
		}
		return listEmployee;
	}
}
