package dev.kurt.controllers;

import java.util.List;


import com.google.gson.Gson;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import dev.kurt.services.ManagerService;
import io.javalin.http.Handler;

public class EmployeeController {
	
	private EmployeeService eServ;
	private ManagerService mServ;
	private Gson gson = new Gson();
	
	public EmployeeController(EmployeeService eServ, ManagerService mServ) {
		super();
		this.eServ = eServ;
		this.mServ = mServ;
	}
	
	public Handler createEmployee = (ctx) ->{
			String employeeJson = ctx.body();
			Employee employee = gson.fromJson(employeeJson, Employee.class);
			eServ.createEmployee(employee);
			ctx.status(201);
			ctx.result(gson.toJson(employee));
	};
	
	public Handler getAllEmployees = (ctx) ->{
		
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
	
	public Handler getEmployeeById = (ctx) ->{
		String id = ctx.pathParam("eid");
		Employee employee = eServ.getEmployeeById(Integer.parseInt(id));	
		String json = gson.toJson(employee);
		ctx.result(json);	
	};
	
	
	
	public Handler updateEmployee = (ctx) ->{
		String employeeJson = ctx.body();
		Employee employee = gson.fromJson(employeeJson, Employee.class);
		eServ.updateEmployee(employee);	
		ctx.result(gson.toJson(employee));
	};
	
	public Handler deleteEmployee = (ctx) ->{
		String id = ctx.pathParam("eid");
		Employee employee = eServ.getEmployeeById(Integer.parseInt(id));
		Boolean result = eServ.deleteEmployee(employee);
		ctx.result(result.toString());
	};
	
	public Handler addReimbursementToEmployee = (ctx) ->{
		
	};
	
	public Handler getEmployeesByManager = (ctx) ->{
		String id = ctx.pathParam("mid");
		Manager manager = mServ.getManagerById(Integer.parseInt(id));
		List<Employee> employees = eServ.getEmployeesByManager(manager);
		String json = gson.toJson(employees);
		ctx.result(json);
		
	};
	
	public Handler addEmployeeToMan = (ctx) ->{
		int id = Integer.parseInt(ctx.pathParam("mid"));
		Employee employee = gson.fromJson(ctx.body(), Employee.class);
		Manager manager = mServ.getManagerById(id);
		employee.setManager(manager);
		eServ.createEmployee(employee);
		ctx.result(gson.toJson(employee));
		ctx.status(201);
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