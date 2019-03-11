package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.EmployeeConverter;
import com.pCarpet.dao.EmployeeDAO;
import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.model.User;

@Service
public class EmployeeService {
	
	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeService(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	public void insertEmployee(EmployeeDTO employee) {
		employeeDAO.save(EmployeeConverter.convertToEntity(employee));
	}
	
	public void deleteEmployeeById(EmployeeDTO employee) {
		employeeDAO.deleteById(employee.getId());
	}
	
	public List<EmployeeDTO> getEmployeeByIdBusinessOwner(int id){
		User businessOwner = new User();
		businessOwner.setId(id);
		return (EmployeeConverter.toListDTO(employeeDAO.findAllByBusinessOwner(businessOwner)));
	}
}
