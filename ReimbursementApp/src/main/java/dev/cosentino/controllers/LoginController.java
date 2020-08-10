package dev.cosentino.controllers;

import com.google.gson.Gson;

import dev.cosentino.entities.Employee;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

public class LoginController {
	
	static EmployeeService eserv = new EmployeeServiceImpl();
	private static Gson gson = new Gson();

	
	public static Handler loginUser = (ctx) ->{
		
		//String empJson = ctx.body();
		
		//Employee emp = gson.fromJson(empJson, Employee.class);
		//Employee employee = eserv.getEmployeeByUsername(emp.getUsername());
		

		
		String username = ctx.queryParam("username");
		String password = ctx.queryParam("password");
		
		if(username != null && password != null) {
			Employee employee = eserv.getEmployeeByUsername(username);
			
			if(employee.getPassword().equals(password)) {
				ctx.sessionAttribute("loggedInAs", employee);
				ctx.status(200);
				ctx.result(gson.toJson(employee));
			}
			else {
				ctx.status(404);
			}
		}
		
	};
	
	
	public static Handler getUserInfo = (ctx) ->{
		Employee emp = ctx.sessionAttribute("loggedInAs");
		Gson gson = new Gson();
		ctx.result(gson.toJson(emp));
	};

}
