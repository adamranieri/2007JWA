package dev.alsabea.app;

import dev.alsabea.controllers.LogInPageController;
import dev.alsabea.controllers.ManagerPageController;
import dev.alsabea.controllers.EmployeePageController;
import io.javalin.Javalin;

public class App {

	
	public static void main(String[] args) {
		
		
		Javalin app= Javalin.create(config ->{
			config.addStaticFiles("frontend");
			
		}).start(7000);
		
		
		
		//login page
		
		app.post("/xyz", LogInPageController.getStaff);
		
		
		
		// employee page
		
		// *** reimbursement form to submit page ***
		
		//create rr
		app.post("/employee/submitRequest", EmployeePageController.createRequest);
		

		//manager page
		
		//update request
		app.put("/manager/judgeRequest", ManagerPageController.updateRequest);

		
	}
}
