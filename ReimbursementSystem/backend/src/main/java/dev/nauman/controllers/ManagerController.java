package dev.nauman.controllers;

import com.google.gson.Gson;

import dev.nauman.services.ReimbursementService;
import dev.nauman.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ManagerController {
	private static ReimbursementService sserv = ReimbursementServiceImpl.getEmployeeServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler FillManagerTable = (ctx) ->{
		ctx.result(gson.toJson(sserv.getAllReimbursements()));
	};
}
