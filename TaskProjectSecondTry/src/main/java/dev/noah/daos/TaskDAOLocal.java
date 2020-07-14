package dev.noah.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.noah.entities.Task;

public class TaskDAOLocal implements TaskDAO {

	private static TaskDAOLocal tdao = null;

	private TaskDAOLocal() {

	};

	public static TaskDAO getTaskDAO() {

		if (tdao == null) {
			tdao = new TaskDAOLocal();
			return tdao;
		} else {
			return tdao;
		}

	}

	private Map<Integer, Task> task_table = new HashMap<Integer, Task>();

	private int counter = 1;

	public Task createNewTask(Task task) {

		task.setId(counter);
		this.task_table.put(task.getId(), task);
		this.counter++;
		return task;

	}

	public Task getTaskById(int id) {
		return task_table.get(id);
	}

	public Task updateTask(Task task) {
		Task taskUpdate = task_table.put(task.getId(), task);
		return taskUpdate;
	}

	public Boolean deleteTaskById(int i) {

		Task taskStatus = task_table.remove(i);
		if (taskStatus != null) {
			return true;
		}
		return false;
	}

	public Set<Task> getAllTasks() {
		Set<Task> tasks = new HashSet<Task>(task_table.values());
		return tasks;
	}

}
