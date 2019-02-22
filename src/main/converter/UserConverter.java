package main.converter;

import main.dto.*;
import main.model.User;

public class UserConverter{

	public static User convertToUser(UserDTO userdto) {
		return (new User(userdto.getID(), userdto.getUsername(), userdto.getPassword(), userdto.getRank()));
	}

	public static UserDTO convertToDto(User user) {
		return (new UserDTO(user.getID(), user.getUsername(), user.getPassword(), user.getRank()));
	}
}

