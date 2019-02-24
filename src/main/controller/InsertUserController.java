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
		String name = request.get("name").toString();
		String surname = request.get("surname").toString();
		String email = request.get("email").toString();
		String phone = request.get("phone").toString();
		int rank;
		
		if(request.get("rank").toString().equals("Y"))
			rank = 1;
		else 
			rank = 0;
		
		userService.insertUser(username, password, name, surname, email, phone, rank);
		MainDispatcher.getInstance().callView("HomeAdmin", request);
	}

}
