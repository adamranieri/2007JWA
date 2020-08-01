package dev.kusch.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.kusch.entities.Employee;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

public class EmployeeController {
	
	private static EmployeeService eserv = new EmployeeServiceImpl();
	private static Gson gson = new Gson();

	public static Handler getEmployee = (ctx) -> {
		
		String userQuery = ctx.queryParam("username");
		String passQuery = ctx.queryParam("password");
		if (userQuery != null) {
			if (passQuery != null) {
				boolean result = eserv.loginEmployee(userQuery, passQuery);
				ctx.result(gson.toJson(result));
				return;
			}
			List<Employee> emps = new ArrayList<Employee>();
			emps = eserv.getEmployeeByUser(userQuery);
			ctx.result(gson.toJson(emps));
			return;
		} else {
			Employee emp = eserv.getEmployeeById(Integer.parseInt(ctx.pathParam("eid")));
			if (emp == null) {
				ctx.status(404);
			} else {
				ctx.status(200);
			}
			ctx.result(gson.toJson(emp));
			return;
		}
		
	};
	
	public static Handler updateEmployee = (ctx) -> {
		String employeeJson = ctx.body();
		Employee emp = gson.fromJson(employeeJson, Employee.class);
		emp = eserv.updateEmployee(emp);
		if (emp == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
		ctx.result(gson.toJson(emp));
	};
	
}
