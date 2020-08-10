package dev.cosentino.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.cosentino.entities.Employee;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

public class EmployeeController {

	public static EmployeeService eserv = new EmployeeServiceImpl();
	private static Gson gson = new Gson();

	public static Handler getAllEmployees = (ctx)->{
		List<Employee> employees = eserv.getAllEmployees(); 
		String json = gson.toJson(employees);
		
		String user = ctx.queryParam("username");
		
		if(user != null) {
			Employee emp = eserv.getEmployeeByUsername(user);
			ctx.result(gson.toJson(emp));
		}
		else {
			ctx.result(json);
		}
	};
	
	public static Handler getEmployeeById = (ctx)->{
		String id = ctx.pathParam("e_id");
		Employee emp = eserv.getEmployeeById(Integer.parseInt(id));
		
		if(emp == null) {
			ctx.status(404);
		}
		else {
			String json = gson.toJson(emp);
			ctx.result(json);
		}
	};
	
//	public static Handler updateEmployee = (ctx)->{
//		String empJson = ctx.body();
//		Employee emp = gson.fromJson(empJson, Employee.class);
//		eserv.updateEmployee(emp);
//		ctx.result(gson.toJson(emp));
//	};
}
