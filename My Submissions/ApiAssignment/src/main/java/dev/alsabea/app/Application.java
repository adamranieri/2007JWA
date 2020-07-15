package dev.alsabea.app;

import dev.alsabea.controller.TaskController;
import io.javalin.Javalin;

public class Application {

	
	public static void main(String[] args) {
		
		
		Javalin app= Javalin.create().start(7000);
		
		app.get("/task", TaskController.getAllTasksHandler);
		app.get("/task/:id", TaskController.getTaskByIdHandler);
		
		
		app.post("/task", TaskController.createHandler);
		
		app.put("/task",TaskController.updateTaskHandler);
		
		app.delete("/task/:id", TaskController.deleteTaskHandler);
		
		
	}
}
