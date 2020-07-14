package dev.ranieri.app;

import dev.ranieri.controllers.SchoolController;
import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7000);
		
		
		// REST APIs should support at a minimum the basic CRUD operations
		// read operations
		app.get("/schools", SchoolController.getAllSchools);// this method returns all schools	
		app.get("/schools/:sid", SchoolController.getSchoolById);// this returns a specific school based on the id
		
		// create operation
		app.post("/schools", SchoolController.createSchool);
		
		//update operation
		app.put("/schools", SchoolController.updateSchool);
		
		//delete operation
		app.delete("/schools/:sid", SchoolController.deleteSchool);
		
	
		
		
	}

}
