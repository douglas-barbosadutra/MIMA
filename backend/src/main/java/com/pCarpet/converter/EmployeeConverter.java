package com.pCarpet.converter;

import java.util.ArrayList;
import java.util.List;

import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.model.Employee;
import com.pCarpet.model.User;

public class EmployeeConverter {

	public static EmployeeDTO convertToDto(Employee employee) {
		EmployeeDTO employeeDTO = null;
		if(employee != null) {
			employeeDTO = new EmployeeDTO();
			employeeDTO.setId(employee.getId());
			employeeDTO.setIdUser(employee.getUser().getId());
			employeeDTO.setName(employee.getUser().getName());
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
			User user = new User();
			user.setId(employeeDTO.getIdUser());
			User businessOwner = new User();
			businessOwner.setId(employeeDTO.getIdBusinessOwner());
			if(employeeDTO.getIdTask() != 0) {
				
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
}
