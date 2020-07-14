package dev.noah.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.noah.entities.Task;
import dev.noah.services.TaskService;
import dev.noah.services.TaskServiceImpl;

@TestMethodOrder(OrderAnnotation.class) // necessary to order your tests
class TaskServiceTests {

public static TaskService tserv = new TaskServiceImpl();
	
	@Test
	@Order(1)
	void createTask() {
		Task task = new Task();
		task.setComplete(false);
		tserv.createTask(task);
		Assertions.assertEquals(1, task.getId());
	}
	
	@Test
	@Order(2)
	void getTaskById() {
		Task task = tserv.getTaskById(1);
		Assertions.assertEquals(1, task.getId());
	}
	
	@Test
	@Order(3)
	void getTaskStatus() {
		Task task = new Task();
		task.setName("Josh");
		task.setComplete(true);
		tserv.createTask(task);
		Set<Task> trueTasks = tserv.getTasksByStatus(true);
		Assertions.assertEquals(1, trueTasks.size());
	}
	
	@Test
	@Order(4)
	void updateTask() {
		Task task = tserv.getTaskById(1);
		task.setName("Charles");
		tserv.updateTask(task);
		Assertions.assertEquals("Charles", tserv.getTaskById(1).getName());
	}
	
	@Test
	@Order(5)
	void deleteTask() {
		tserv.deleteTaskById(2);
		Assertions.assertEquals(null, tserv.getTaskById(2));
	}
}

