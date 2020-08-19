package dev.nauman.controllers;

import com.google.gson.Gson;

import dev.nauman.entities.Employee;
import dev.nauman.entities.Reimbursement;
import dev.nauman.services.EmployeeService;
import dev.nauman.services.EmployeeServiceImpl;
import dev.nauman.services.ReimbursementService;
import dev.nauman.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ReimbursementController {

	private static ReimbursementService sserv = ReimbursementServiceImpl.getEmployeeServiceImpl();
	private static EmployeeService eserv = EmployeeServiceImpl.getEmployeeServiceImpl();
	
	private static Gson gson = new Gson();
	
	public static Handler SubmitReimbursement = (ctx)->{
		String reimbursementJSON = ctx.body();
		String eIdString = ctx.pathParam("eId");
		System.out.println(eIdString);
		int eId = Integer.parseInt(eIdString);
		Employee employee = eserv.getEmployeeById(eId);
		
		Reimbursement reimbursement = gson.fromJson(reimbursementJSON, Reimbursement.class);
		reimbursement.setemployee(employee);
		sserv.submitReimbursement(reimbursement);
		ctx.status(201);
	};
	
	
	public static Handler AscendingItem = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByItemReimbursements(ascending)));
	};
	public static Handler AscendingStatus = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByStatusReimbursements(ascending)));
	};
	public static Handler AscendingCategory = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByCategoryReimbursements(ascending)));
	};
	public static Handler AscendingAmount = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByAmountReimbursements(ascending)));
	};
	public static Handler AscendingEmployee = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByEmployeeReimbursements(ascending)));
	};
	public static Handler AscendingNote = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByNoteReimbursements(ascending)));
	};
	public static Handler AscendingDate = (ctx) ->{
		String ascendingString =ctx.queryParam("ascending");
		boolean ascending = ascendingString.contains("true") ? true : false;
		ctx.result(gson.toJson(sserv.getAllAcendingByDateReimbursements(ascending)));
	};
}
