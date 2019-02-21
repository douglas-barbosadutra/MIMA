package main.model;

public class Session {
	
	private static User user = null;
	
	public static void setUserSession(User u) {
		user = u;
	}
	
	public static User getUserSession() {
		return user;
	}

}
