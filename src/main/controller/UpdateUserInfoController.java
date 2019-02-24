package main.controller;

import main.MainDispatcher;
import main.service.UserService;

public class UpdateUserInfoController implements Controller{
	private UserService userService;
	
	public UpdateUserInfoController() {
		userService = new UserService();
	}

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		String attribute = request.get("attribute").toString();
		String value = request.get("value").toString();
		userService.updateUser(attribute, value);
		MainDispatcher.getInstance().callAction("Home", "doControl", null);
	}

}
