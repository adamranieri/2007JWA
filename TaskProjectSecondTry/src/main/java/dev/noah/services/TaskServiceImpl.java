package dev.noah.services;

import java.util.HashSet;
import java.util.Set;

import dev.noah.daos.TaskDAO;
import dev.noah.daos.TaskDAOLocal;
import dev.noah.entities.Task;

public class TaskServiceImpl implements TaskService {

	public static TaskDAO tdao = TaskDAOLocal.getTaskDAO();

	public Task createTask(Task task) {
		Task newTask = tdao.createNewTask(task);

		return newTask;
	}

	public Task getTaskById(int id) {
		return tdao.getTaskById(id);
	}

	public Set<Task> getTasksByStatus(boolean statusType) {

		Set<Task> desiredTaskStatus = new HashSet<Task>();

		for (Task task : tdao.getAllTasks()) {
			if (task.isComplete() == statusType) {
				desiredTaskStatus.add(task);
			}
		}

		return desiredTaskStatus;
	}

	public Task updateTask(Task task) {

		return tdao.updateTask(task);
	}

	public Boolean deleteTaskById(int id) {
		return tdao.deleteTaskById(id);
	}

}
