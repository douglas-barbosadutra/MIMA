package main.controller;

import java.util.List;

import main.MainDispatcher;
import main.model.User;
import main.service.UserService;

public class ShowUsersController implements Controller{
	private UserService userService;
	
	public ShowUsersController() {
		userService = new UserService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		List<User> users = userService.getAllUsers();
		request.put("users", users);
		MainDispatcher.getInstance().callView("ShowUsers", request);
	}

}
