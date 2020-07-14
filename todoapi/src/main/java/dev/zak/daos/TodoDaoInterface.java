package dev.zak.daos;

import java.util.Set;

import dev.zak.entities.Task;

public interface TodoDaoInterface {

	public Task createTask(Task t);
	
	public Task getTaskById(int id);
	
	public Set<Task> getAllTasks();
	
	public Task updateTask(Task t);
	
	public boolean deleteTask(int id);
}
