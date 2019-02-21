package main.controller;

import main.MainDispatcher;

public class MachineManagementController implements Controller {

    public MachineManagementController() {
    }

    public void doControl(Request request) {
    	MainDispatcher.getInstance().callView("MachineManagement", request);
    }
}

