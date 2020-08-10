package dev.kusch.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.kusch.daos.EmployeeDAO;
import dev.kusch.daos.EmployeeDAOHibernate;
import dev.kusch.entities.Employee;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import io.javalin.http.Handler;

public class EmployeeController {
	
	private static EmployeeDAO edao = EmployeeDAOHibernate.getEmployeeDAOHibernate();
	
	private static EmployeeService eserv = new EmployeeServiceImpl(edao);
	
	private static Gson gson = new Gson();

	public static Handler getEmployee = (ctx) -> {
		Employee emp = eserv.getEmployeeById(Integer.parseInt(ctx.pathParam("eid")));
		if (emp == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
		ctx.result(gson.toJson(emp));
		return;
	};
	
	public static Handler updateEmployee = (ctx) -> {
		String employeeJson = ctx.body();
		Employee emp = gson.fromJson(employeeJson, Employee.class);
		System.out.println(emp);
		emp = eserv.updateEmployee(emp);
		if (emp == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
		ctx.result(gson.toJson(emp));
	};
	
}
