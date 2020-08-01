package dev.alsabea.app;

import dev.alsabea.controllers.LogInPageController;
import io.javalin.Javalin;

public class App {

	
	public static void main(String[] args) {
		
		
		Javalin app= Javalin.create(config ->{
			config.addStaticFiles("frontend");
			
		}).start(7000);
		
		
		
		//login page
		
		app.post("/xyz", LogInPageController.getStaff);
		
		
		// employee page
		
			// reimbursement form to submit page
		
		
		//manager page
		
			//list of employee page
		
			
			//employee requests page, pending and already judged requests page
			
		
		
		
		//app.get
		
		//Employee
		
		//app.get("", handler)
		
		
		//Manager
		
		
		//Reimbursement Requests

		
	}
}
