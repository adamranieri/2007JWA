package dev.noah.daos;

import java.util.Set;

import dev.noah.entities.Task;

public interface TaskDAO {

	
	//create
	Task createNewTask(Task task);
	
	//read
	Task getTaskById(int id);
	Set<Task> getAllTasks();
	
	//Update
	Task  updateTask(Task task);
	
	//Destroy
	Boolean deleteTaskById(int i);
}
