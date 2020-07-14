package dev.zak.services;

import java.util.HashSet;
import java.util.Set;

import dev.zak.daos.TodoDao;
import dev.zak.daos.TodoDaoInterface;
import dev.zak.entities.Task;

public class TaskServices implements TaskServicesInterface{
	
	private static TodoDaoInterface  tdao = TodoDao.getTaskDao();
	
	public TaskServices() {
		// TODO Auto-generated constructor stub
	}

	public Task createTask(Task t) {
		
		return tdao.createTask(t);
	}

	public Task UpdateTask(Task t) {
		return tdao.updateTask(t);
	}

	public Set<Task> getAllTasks() {
		return tdao.getAllTasks();
	}

	public Task getTaskById(int id) {
		return tdao.getTaskById(id);
	}

	public boolean deleteTaskById(int id) {
		return tdao.deleteTask(id);
	}

	public Set<Task> getTaskByStatus(boolean isComplete) {
		Set<Task> allTasks = tdao.getAllTasks();
		Set<Task> result = new HashSet<Task>();
		for(Task t : allTasks) {
			if(t.isComplete() == isComplete)
				result.add(t);
		}
		return result;
	}

}
