package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.converter.EmployeeConverter;
import com.pCarpet.dao.EmployeeDAO;
import com.pCarpet.dto.EmployeeDTO;
import com.pCarpet.model.Employee;
import com.pCarpet.model.User;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public EmployeeService() {	}
	
	public EmployeeDTO insertEmployee(EmployeeDTO employeeDTO) {
		Employee employee = EmployeeConverter.convertToEntity(employeeDTO);
		return EmployeeConverter.convertToDto(employeeDAO.saveAndFlush(employee));
	}
	
	public boolean deleteEmployeeById(int idEmployee) {
		this.employeeDAO.deleteById(idEmployee);
		return true;
	}
	
	public List<EmployeeDTO> getEmployeeByIdBusinessOwner(int id){
		User businessOwner = new User();
		businessOwner.setId(id);
		return (EmployeeConverter.toListDTO(employeeDAO.findAllByBusinessOwner(businessOwner)));
	}
	
	public EmployeeDTO getEmployeeByUser(int idUser) {
		User user = new User();
		user.setId(idUser);
		return EmployeeConverter.convertToDto(employeeDAO.findEmployeeByUser(user));
	}
	
	
}
