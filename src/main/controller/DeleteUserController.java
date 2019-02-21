package main.controller;

import main.MainDispatcher;
import main.service.UserService;

public class DeleteUserController implements Controller{
	
	private UserService userService;
	
	public DeleteUserController() {
		userService = new UserService();
	}
	

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		int id = (int) request.get("id");
		
		if(id != 0)
			userService.deleteUser(id);
		
		MainDispatcher.getInstance().callView("HomeAdmin", null);
		
	}

}
