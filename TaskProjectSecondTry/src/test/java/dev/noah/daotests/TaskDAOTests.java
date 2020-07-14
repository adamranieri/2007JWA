package dev.noah.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.noah.daos.TaskDAO;
import dev.noah.daos.TaskDAOLocal;
import dev.noah.entities.Task;

@TestMethodOrder(OrderAnnotation.class) // necessary to order your tests
class TaskDAOTests {

	public static TaskDAO tdao = TaskDAOLocal.getTaskDAO();
	
	@Test
	@Order(1)
	void createNewTask() {
		Task task = new Task(0,"get charles", false);
		tdao.createNewTask(task);
		Assertions.assertEquals(1,task.getId());
	}
	
	@Test
	@Order(2)
	void getTaskById(){
		Task task = tdao.getTaskById(1);
		Assertions.assertEquals(1, task.getId());
	}
	
	@Test
	@Order(3)
	void updateTask() {
		Task task = tdao.getTaskById(1);
		task.setName("Toasty");
		Assertions.assertEquals("Toasty", task.getName());
	}
	
	@Test
	@Order(4)
	void deleteTask() {
		Task task = tdao.getTaskById(1);
		boolean result = tdao.deleteTaskById(1);
		Assertions.assertEquals(true, result);
	}
}
