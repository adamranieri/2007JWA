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
		
		
		app.post("/xyz/login", LogInPageController.getStaff);
		

		app.post("/employee/:id/submitRequest", EmployeePageController.createRequest);
		

		app.put("/manager/:id/judgeRequest", ManagerPageController.updateRequest);

		
		app.post("/manager/:id/getUpdatedManager", ManagerPageController.getUpdatedManager);
		
	}
}
