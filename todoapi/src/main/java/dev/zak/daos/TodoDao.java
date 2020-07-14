package dev.zak.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.zak.entities.Task;

public class TodoDao implements TodoDaoInterface{

	private static TodoDao tdao= null;
	private Map<Integer, Task> taskDB= new HashMap<Integer, Task>();
	private int counter = 1;
	
	public static TodoDaoInterface getTaskDao() {
		if(tdao == null) {
			tdao = new TodoDao();
		}
		return tdao;
	}
	
	private TodoDao() {
		// TODO Auto-generated constructor stub
	}

	
	public Task createTask(Task t) {
		t.setId(this.counter);
		this.taskDB.put(this.counter, t);
		this.counter++;
		return t;
	}

	public Task getTaskById(int id) {
		return taskDB.get(id);
	}

	
	public Set<Task> getAllTasks() {
		Set<Task> tasks = new HashSet<Task>(taskDB.values());
		return tasks;
	}

	public Task updateTask(Task t) {
		taskDB.put(t.getId(), t);
		return t;
	}

	public boolean deleteTask(int id) {
		Task t = taskDB.remove(id);
		if(t != null)
			return true;
		else
			return false;
	}
	
	

}
