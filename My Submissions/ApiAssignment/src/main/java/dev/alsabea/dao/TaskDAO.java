package dev.alsabea.dao;

import java.util.Set;

import dev.alsabea.entities.Task;



public interface TaskDAO {

	
	Task createTask(Task task); // think of this as a save method
	
	//Read
	Task getTaskById(int id);
	Set<Task> getAllTasks();
	
	//Update
	Task updateTask(Task task);
	
	
	//Delete
	boolean deleteTask(int id);
	
	
}
