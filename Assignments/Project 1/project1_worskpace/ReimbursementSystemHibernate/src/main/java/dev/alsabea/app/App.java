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
		
		
		app.post("/xyz", LogInPageController.getStaff);
		

		app.post("/employee/submitRequest", EmployeePageController.createRequest);
		

		app.put("/manager/judgeRequest", ManagerPageController.updateRequest);

		
		app.post("/manager/getUpdatedManager", ManagerPageController.getUpdatedManager);
		
	}
}
