package com.mima.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mima.converter.UserConverter;
import com.mima.dao.UserDAO;
import com.mima.dto.UserDTO;
import com.mima.model.User;


@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public UserService() {	}

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
