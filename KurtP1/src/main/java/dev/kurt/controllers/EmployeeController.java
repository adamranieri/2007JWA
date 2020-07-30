package dev.kurt.controllers;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import dev.kurt.entities.Employee;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

public class EmployeeController {
	
	public static EmployeeService eServ = new EmployeeServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler createEmployee = (ctx) ->{
		String employeeJson = ctx.body();
		Employee employee = gson.fromJson(employeeJson, Employee.class);
		eServ.createEmployee(employee);
		ctx.status(201);
		ctx.result(gson.toJson(employee));
	};
	
	public static Handler getAllEmployees = (ctx) ->{
		
		String userQ = ctx.queryParam("username");
		String passQ = ctx.queryParam("password");
		
		switch ((userQ != null) + "-" + (passQ != null)) {
	    case "false-false":
	    	List<Employee> employees = eServ.getAllEmployees();
	    	ctx.result(gson.toJson(employees));
	        break;
	    case "false-true":
	        break;
	    case "true-false":
	    	break;
	    case "true-true":
	    	Employee employee = eServ.getEmployeeByLogin(userQ, passQ);
	    	ctx.result(gson.toJson(employee));
		    break;
	    default:
	        throw new RuntimeException("404: Something Broke");
		}
		
	};

}

/*
	public static Handler Login = (ctx) ->{
	ctx.sessionAttribute("loggedIn", true);
	};
	
	public static Handler getSensitiveInfo = (ctx) ->{
	Boolean isLoggedIn = ctx.sessionAttribute("loggedIn");
	if(isLoggedIn == null) {
		ctx.status(403);
	}else {
		ctx.status(200);
	}
		
	};
	
	
	
	app.post("/login",LoginController.Login);
	app.post("/info",LoginController.getSensitiveInfo);
*/