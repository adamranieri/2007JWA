package dev.nauman.controllers;

import io.javalin.http.Handler;

import com.google.gson.Gson;

import dev.nauman.entities.Employee;
import dev.nauman.services.EmployeeService;
import dev.nauman.services.EmployeeServiceImpl;
import dev.nauman.services.ReimbursementService;
import dev.nauman.services.ReimbursementServiceImpl;

public class EmployeeController {

	private static EmployeeService eserv = EmployeeServiceImpl.getEmployeeServiceImpl();
	private static ReimbursementService rserv = ReimbursementServiceImpl.getEmployeeServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler FillEmployeeTable = (ctx) ->{
		String eIdString = ctx.sessionAttribute("e_id");
		int eId = Integer.parseInt(eIdString);
		ctx.result(gson.toJson(rserv.getAllEmployeeReimbursements(eId)));
	};
	

}
