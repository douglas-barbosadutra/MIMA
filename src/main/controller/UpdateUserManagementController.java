package main.controller;

import main.MainDispatcher;

public class UpdateUserManagementController implements Controller{

	@Override
	public void doControl(Request request) {
		// TODO Auto-generated method stub
		MainDispatcher.getInstance().callView("UpdateUserManagement", null);
	}

}
