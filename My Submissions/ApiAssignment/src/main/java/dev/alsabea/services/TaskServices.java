package dev.alsabea.services;

import java.util.Set;

import dev.alsabea.entities.Task;

public interface TaskServices {

	
	Task createTask(Task task);
	
	Set<Task> getAllTasks();
	
	Task getById(int id);
	
	Task updateTask(Task task);
	
	boolean deleteTask(int id);
	
	
}
