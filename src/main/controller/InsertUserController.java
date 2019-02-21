package main.controller;

import main.MainDispatcher;
import main.service.UserService;

public class InsertUserController implements Controller{
	private UserService userService;
	
	public InsertUserController() {
		userService = new UserService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		String username = request.get("username").toString();
		String password = request.get("password").toString();
		userService.insertUser(username, password);
		MainDispatcher.getInstance().callView("HomeAdmin", request);
	}

}
