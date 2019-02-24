package main.controller;

import main.MainDispatcher;

public class UpdateUserController implements Controller{

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		
		try {
			int choice = Integer.parseInt(request.get("choice").toString());
			
			switch(choice) {
				case 1:{
					request.put("attribute", "email");
				}break;
				
				case 2:{
					request.put("attribute", "phone");
				}break;
				
				case 3:{
					request.put("attribute", "username");
				}break;
				
				case 4:{
					request.put("attribute", "password");
				}break;
				
				default:{
					MainDispatcher.getInstance().callView("UpdateUserManagement", null);
				}break;
			}
			MainDispatcher.getInstance().callView("UpdateUser", request);
			
		} catch(NumberFormatException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
		}
	}

}
