package dev.zak.services;

import java.util.Set;

import dev.zak.entities.Task;

public interface TaskServicesInterface {

		public Task createTask(Task t);
		
		public Task UpdateTask(Task t);
		
		public Set<Task> getAllTasks();
		public Task getTaskById(int id);
		
		public boolean deleteTaskById(int id);
		
		public Set<Task> getTaskByStatus(boolean isComplete);
}
