package dev.zak.app;

import dec.zak.controllers.TaskHandler;
import io.javalin.Javalin;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Javalin app = Javalin.create();
		app.start(7000);
		
		app.get("/tasks", TaskHandler.getAllTasks);
		app.get("/tasks/:tid", TaskHandler.getTaskByID);
		
		app.post("/tasks", TaskHandler.createTask);
		
		app.put("/tasks/:tid", TaskHandler.updateTask);
		
		app.delete("/tasks/:tid", TaskHandler.deleteTask);
		
		

	}

}
