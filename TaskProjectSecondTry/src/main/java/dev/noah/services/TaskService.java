package dev.noah.services;

import java.util.Set;

import dev.noah.entities.Task;

public interface TaskService {
	//Create
	Task createTask(Task task);
	
	//Read
	Task getTaskById(int id);
	Set<Task> getTasksByStatus(boolean statusType);
	
	//Update
	Task updateTask(Task task);
	
	//Destroy
	Boolean deleteTaskById(int id);
}
