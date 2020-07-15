package dev.alsabea.controller;

import java.util.Set;

import com.google.gson.Gson;

import dev.alsabea.entities.Task;
import dev.alsabea.services.TaskServices;
import dev.alsabea.services.TaskServicesImpl;
import io.javalin.http.Handler;

public class TaskController {

	
	public static TaskServices ts= new TaskServicesImpl();
	private static Gson gson= new Gson();
	
	public static Handler createHandler= (ctx) ->{
		
		String jstr= ctx.body();
		Task t =gson.fromJson(jstr, Task.class);
		ts.createTask(t);
		ctx.status(201);
		ctx.result(gson.toJson(t));
	};
	
	
	public static Handler getAllTasksHandler = (ctx) -> {
		Set<Task> tasks = ts.getAllTasks();
		String json= gson.toJson(tasks);
		ctx.result(json);
	};
	
	
	public static Handler getTaskByIdHandler =  (ctx) -> {
		
		String id =ctx.pathParam("id");
		Task t = ts.getById(Integer.parseInt(id));
		String json=gson.toJson(t);
		ctx.result(json);
	};
	
	public static Handler updateTaskHandler = (ctx) ->{
		
		String taskJson= ctx.body();
		Task t = gson.fromJson(taskJson, Task.class);
		ts.updateTask(t);
		ctx.result(gson.toJson(t));
		
	};
	
	public static Handler deleteTaskHandler = (ctx) -> {
		
		String id= ctx.pathParam("id"); 
		boolean result = ts.deleteTask(Integer.parseInt(id));
		ctx.result(""+result);
		
	};
	
	
	
	
	
}
