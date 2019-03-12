package com.pCarpet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.UserDAO;
import com.pCarpet.dto.UserDTO;
import com.pCarpet.model.User;
import com.pCarpet.converter.UserConverter;


@Service
public class UserService {

	private UserDAO userDAO;
	

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean deleteUser(int id) {
		this.userDAO.deleteById(id);
		return true;
	}

	public UserDTO insertUser(UserDTO userDTO) {
		User user = UserConverter.toEntity(userDTO);
		userDAO.saveAndFlush(user);
		return UserConverter.toDTO(user);
	}
	
	public List<UserDTO> getAllUsers(){
		return UserConverter.toListDTO(userDAO.findAll());
	}
	
	public UserDTO findUserById(int id) {
		return UserConverter.toDTO(userDAO.findUserById(id));
	}

	public UserDTO getUserByUsername(String username) {
		return UserConverter.toDTO(userDAO.findUserByUsername(username));
	}

}
