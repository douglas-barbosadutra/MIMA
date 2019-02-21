package main.view;

import java.util.List;

import main.MainDispatcher;
import main.controller.Request;
import main.model.User;

public class ShowUsersView implements View{
	private List<User> users;

	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		users = (List<User>) request.get("users");
	}

	@Override
	public void showOptions() {
		// TODO Auto-generated method stub
		System.out.println("---LISTA UTENTI---");
		for(User u : users)
			System.out.println(u);
	}

	@Override
	public String getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		MainDispatcher.getInstance().callAction("User", "doControl", null);
	}

}
