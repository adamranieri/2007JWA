package dev.cosentino.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

import dev.cosentino.entities.Employee;
import dev.cosentino.entities.Reimbursement;
import dev.cosentino.services.EmployeeService;
import dev.cosentino.services.EmployeeServiceImpl;
import dev.cosentino.services.ReimbursementImpl;
import dev.cosentino.services.ReimbursementService;
import io.javalin.http.Handler;

public class ReimbursementController {

	private static ReimbursementService rserv = new ReimbursementImpl();	
	private static EmployeeService eserv = new EmployeeServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler createReimbursement = (ctx)->{
		int empId = Integer.parseInt(ctx.pathParam("e_id"));
		Reimbursement reim = gson.fromJson(ctx.body(), Reimbursement.class);
		
		reim.setEmployee(eserv.getEmployeeById(empId));
		reim.setSubmitted_date(Date.valueOf(LocalDate.now()).toString());
		reim.setStatus("Submitted");
		reim.setManager_note("");
		
		rserv.createReimbursement(reim);
		String json = gson.toJson(reim);
		
		ctx.status(201);
		ctx.result(json);
	};
	
	public static Handler getAllReimbursements = (ctx)->{
  		List<Reimbursement> reims = rserv.getAllReimbursements();
  		
  		String status = ctx.queryParam("status");
  		
  		if(status != null) {
  			List<Reimbursement> reimStatus = rserv.getReimbursementByStatus(status);
  			ctx.status(200);
  			ctx.result(gson.toJson(reimStatus));
  		}
  		else {
  			ctx.status(200);
  			ctx.result(gson.toJson(reims));
  		}
  	};
  	
  	public static Handler getReimbursementByEmpId = (ctx)->{
  		int empId = Integer.parseInt(ctx.pathParam("e_id"));
  		Employee emp = eserv.getEmployeeById(empId);
  		List<Reimbursement> reims = rserv.getReimbursementsByEmployeeId(empId);
  		
  		String status = ctx.queryParam("status");
  		
  		if(status != null) {
  			List<Reimbursement> reimStatus = rserv.getReimbursementByEmployeeAndStatus(emp, status);
  			ctx.status(200);
  			ctx.result(gson.toJson(reimStatus));
  		}
  		else {
  			ctx.status(200);
  			ctx.result(gson.toJson(reims));
  		}
  	};
	public static Handler getReimbursementById = (ctx)->{
		int id = Integer.parseInt(ctx.pathParam("r_id"));
		Reimbursement reim = rserv.getReimbursementById(id);
		String json = gson.toJson(reim);
		ctx.status(200);
		ctx.result(json);
	};
	
	public static Handler updateReimbursement = (ctx)->{
		String reimJson = ctx.body();
		//int empId = Integer.parseInt(ctx.pathParam("e_id"));
		int rId = Integer.parseInt(ctx.pathParam("r_id"));
		
		//Employee emp = eserv.getEmployeeById(empId);
		Reimbursement r = rserv.getReimbursementById(rId);
		Reimbursement reim = gson.fromJson(reimJson, Reimbursement.class);
		
		r.setManager_note(reim.getManager_note());
		r.setStatus(reim.getStatus());		

		rserv.updateReimbursement(r);
		ctx.status(200);
		ctx.result(gson.toJson(r));
	}; 
}
