package dev.ranieri.app;

import dev.ranieri.controllers.LoginController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		LoginController loginController = new LoginController();
		
		app.get("/cookie",loginController.giveCookie);
		
		app.post("/login", loginController.createSession);
		
		app.get("/userinfo", loginController.getUserInfo);
		
		
	}

}
