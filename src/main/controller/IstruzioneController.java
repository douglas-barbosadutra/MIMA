package main.controller;

import main.MainDispatcher;

public class IstruzioneController implements Controller{

	public void doControl(Request request) {
		if(request != null) {
			int choice = 0;
			try {
				choice = Integer.parseInt(request.get("choice").toString());
			}
			catch(NumberFormatException e) {
				System.out.println("Inserisci un comando valido");
				MainDispatcher.getInstance().callView("Istruzione", null);
			}
			switch (choice) {
				case 1: {
					MainDispatcher.getInstance().callAction("ShowIstruzione", "doControl", null);
					break;
				}
				case 2: {
					MainDispatcher.getInstance().callAction("InsertIstruzione", "doControl", null);
			        break;
					}
				case 3: {
					MainDispatcher.getInstance().callAction("ModifyIstruzione", "doControl", null);
					break;
				}
				case 4: {
					MainDispatcher.getInstance().callAction("DeleteIstruzione", "doControl", null);
					break;
					}
				case 5: {
					MainDispatcher.getInstance().callAction("TaskManagement", "doControl", null);
					break;
				}
				default: {
					System.out.println("Inserisci un comando valido");
					MainDispatcher.getInstance().callView("Istruzione", null);
				}
			}
		}
		else {
			MainDispatcher.getInstance().callView("Istruzione", null);
		}
	}
	
	
}
