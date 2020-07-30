package dev.kurt.app;

import dev.kurt.controllers.EmployeeController;
import io.javalin.Javalin;

public class App {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/frontend");
		}).start(7000);
		
		app.get("/employees", EmployeeController.getAllEmployees);
		
		app.post("/employees", EmployeeController.createEmployee);
	}
}
