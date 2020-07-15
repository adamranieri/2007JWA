package dev.alsabea.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.alsabea.entities.Task;


public class TaskDAOImpl implements TaskDAO{

	
	
private static TaskDAOImpl dao = null;
	
	private TaskDAOImpl() {
		
	};
	
	public static TaskDAO getTaskDAO() {
		
		if(dao == null) {
			dao = new TaskDAOImpl();
			return dao;
		}else {
			return dao;
		}
		
	}
	
	// we are going to emulate a database by using a map to store our Tasks in
	private Map<Integer,Task> Task_table = new HashMap<Integer,Task>();
	
	private int counter = 1;


	public Task createTask(Task Task) {
		Task.setId(counter);
		this.Task_table.put(Task.getId(), Task);
		this.counter++;	
		return Task;
	}


	public Task getTaskById(int id) {	
		return Task_table.get(id);
	}


	public Set<Task> getAllTasks() {
		Set<Task> Tasks = new HashSet<Task>(Task_table.values());
		return Tasks;
	}


	public Task updateTask(Task Task) {
		Task_table.put(Task.getId(), Task);
		return Task;
	}


	public boolean deleteTask(int id) {
		
		Task s = Task_table.remove(id);
		
		if(s != null) {
			return true;
		}else {
			return false;
		}
		
	}
}
