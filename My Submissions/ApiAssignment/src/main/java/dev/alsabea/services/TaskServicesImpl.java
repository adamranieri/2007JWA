package dev.alsabea.services;

import java.util.Set;

import dev.alsabea.dao.TaskDAO;
import dev.alsabea.dao.TaskDAOImpl;
import dev.alsabea.entities.Task;

public class TaskServicesImpl implements TaskServices {

	TaskDAO tdao= TaskDAOImpl.getTaskDAO();
	
	@Override
	public Task createTask(Task task) {
		// TODO Auto-generated method stub
		
		return tdao.createTask(task);
	}
	
	
	public Set<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return tdao.getAllTasks();
	}

	public Task getById(int id) {
		// TODO Auto-generated method stub
		
		return tdao.getTaskById(id);
	}

	public Task updateTask(Task task) {
		// TODO Auto-generated method stub
		
		return tdao.updateTask(task);
	}

	public boolean deleteTask(int id) {
		// TODO Auto-generated method stub
		
		return tdao.deleteTask(id);
	}



}
