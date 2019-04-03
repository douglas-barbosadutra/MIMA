package com.UserMicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserMicroservice.converter.UserConverter;
import com.UserMicroservice.dao.UserDAO;
import com.UserMicroservice.dto.UserDTO;
import com.UserMicroservice.model.User;

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
		return UserConverter.toDTO(userDAO.saveAndFlush(user));
	}
	
	public List<UserDTO> getAllUsers(){
		return UserConverter.toListDTO(userDAO.findAll());
	}
	
	public UserDTO findUserById(int id) {
		Optional<User> user = userDAO.findById(id);
		
		if(user.isPresent())
			return UserConverter.toDTO(user.get());
		else
			return null;
	}

	public UserDTO getUserByUsername(String username) {
		return UserConverter.toDTO(userDAO.findUserByUsername(username));
	}

}
