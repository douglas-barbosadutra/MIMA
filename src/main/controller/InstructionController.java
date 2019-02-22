package main.controller;

import main.MainDispatcher;

public class InstructionController implements Controller{

	public void doControl(Request request) {
		if(request != null) {
			int choice = 0;
			try {
				choice = Integer.parseInt(request.get("choice").toString());
			}
			catch(NumberFormatException e) {
				MainDispatcher.getInstance().callView("Instruction", null);
			}
			switch (choice) {
				case 1: {
					MainDispatcher.getInstance().callAction("ShowInstruction", "doControl", null);
					break;
				}
				case 2: {
					MainDispatcher.getInstance().callAction("InsertInstruction", "doControl", null);
			        break;
					}
				case 3: {
					MainDispatcher.getInstance().callAction("ModifyInstruction", "doControl", null);
					break;
				}
				case 4: {
					MainDispatcher.getInstance().callAction("DeleteInstruction", "doControl", null);
					break;
					}
				case 5: {
					MainDispatcher.getInstance().callAction("TaskManagement", "doControl", null);
					break;
				}
				default: {
					System.out.println("Inserisci un comando valido");
					MainDispatcher.getInstance().callView("Instruction", null);
				}
			}
		}
		else {
			MainDispatcher.getInstance().callView("Instruction", null);
		}
	}
	
	
}
