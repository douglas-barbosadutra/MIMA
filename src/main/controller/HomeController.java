package main.controller;

import main.MainDispatcher;
import main.model.User;
import main.service.LoginService;
import main.service.UserService;

public class HomeController implements Controller {

    private LoginService loginService;

    public HomeController() {
        loginService = new LoginService();
    }

    public void doControl(Request request) {
    	if (request != null) {

	        String username = request.get("username").toString();
	        String password = request.get("password").toString();
	        
	        User user = loginService.login(username, password);
	        //System.out.println(user);
	        
	        if (user != null) {
	        	UserService.setUserSession(user);
	        	if(user.isAdmin()) {
		            MainDispatcher.getInstance().callView("HomeAdmin", null);
		        	//System.out.println("amministraotre");
	        	}else {
	        		MainDispatcher.getInstance().callView("HomeUser", null);
	                //System.out.println("utente");
	        	}
	        }else{
	        	//System.out.println("Nessun utente trovato");
	        	MainDispatcher.getInstance().callAction("Login", "doControl", null);
	        }
        
    	}else {    
    		
    		if(!UserService.getUserSession().isAdmin()) {
    			MainDispatcher.getInstance().callView("HomeUser", null);
    		}else {
    			MainDispatcher.getInstance().callView("HomeAdmin", null);
    		}

    	}

    }
}
