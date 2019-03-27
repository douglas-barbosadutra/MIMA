package com.mima.converter;

import java.util.ArrayList;
import java.util.List;

import com.mima.dto.UserDTO;
import com.mima.model.User;

public class UserConverter{

	public static UserDTO toDTO(User user) {
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setUsername(user.getUsername());
			userDTO.setPassword(user.getPassword());
			userDTO.setName(user.getName());
			userDTO.setEmail(user.getEmail());
			userDTO.setRank(user.getRankUser());
			userDTO.setSurname(user.getSurname());
			userDTO.setPhone(user.getPhone());
		}
		return userDTO;
	}

	public static User toEntity(UserDTO userDTO) {
		User user = null;
		if (userDTO != null) {
			user = new User();
			user.setId(userDTO.getId());
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			user.setRankUser(userDTO.getRank());
			user.setSurname(userDTO.getSurname());
			user.setPhone(userDTO.getPhone());
			user.setPassword(userDTO.getPassword());
			user.setUsername(userDTO.getUsername());
		}
		return user;
	}
	
	
	public static List<UserDTO> toListDTO(List<User> list) {
		List<UserDTO> listUserDTO = new ArrayList<>();
		if (!list.isEmpty()) {
			for (User user : list) {
				listUserDTO.add(UserConverter.toDTO(user));
			}
		}
		return listUserDTO;
	}
}

