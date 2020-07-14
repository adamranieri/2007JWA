package dec.zak.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.zak.entities.Task;
import dev.zak.services.TaskServices;
import dev.zak.services.TaskServicesInterface;
import io.javalin.http.Handler;

public class TaskHandler {
	
	public static TaskServicesInterface tServs= new TaskServices();
	public static Gson gson = new Gson();
	
	public TaskHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Handler createTask = (ctx)->{
		Task t = gson.fromJson(ctx.body(), Task.class);
		Task newTask = tServs.createTask(t);
		String result = gson.toJson(newTask);
		ctx.result(result);
	};
	
	public static Handler getAllTasks = (ctx)->{
		Set<Task> allTasks = null;
		
		//Query isComplete
		String complete = ctx.queryParam("complete");
		String incomplete = ctx.queryParam("incomplete");
		if(complete != null) {
			allTasks = tServs.getTaskByStatus(true);
		}
		else if(incomplete != null) {
			allTasks = tServs.getTaskByStatus(false);
		}
		else {
			allTasks = tServs.getAllTasks();
		}
		String jsonTasks = gson.toJson(allTasks);
		ctx.result(jsonTasks);
		
		
	};
	
	public static Handler getTaskByID = (ctx)->{
		int id = Integer.parseInt(ctx.pathParam("tid"));
		Task t = tServs.getTaskById(id);
		String jsonTask = gson.toJson(t);
		ctx.result(jsonTask);
	};
	
	public static Handler updateTask = (ctx)->{
		Task t = gson.fromJson(ctx.body(), Task.class);
		Task result = tServs.UpdateTask(t);
		String jsonTask = gson.toJson(result);
		ctx.result(jsonTask);
	};
	
	public static Handler deleteTask = (ctx)->{
		int id = Integer.parseInt(ctx.pathParam("tid"));
		boolean result = tServs.deleteTaskById(id);
		if(result)
			ctx.result("Item Deleted !!");
		else
			ctx.result("Deletion Problem !!");
		
	};
	
	
	
	
	
	
	
	

}
