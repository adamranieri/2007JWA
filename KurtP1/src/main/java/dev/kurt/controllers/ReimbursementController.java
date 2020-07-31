package dev.kurt.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.kurt.entities.Employee;
import dev.kurt.entities.Reimbursement;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.EmployeeServiceImpl;
import dev.kurt.services.ReimbursementService;
import dev.kurt.services.ReimbursementServiceImpl;
import io.javalin.http.Handler;

public class ReimbursementController {

	private ReimbursementService reiServ; 
	private EmployeeService eServ;
	private Gson gson = new Gson();
	
	public ReimbursementController(EmployeeService eServ, ReimbursementService reiServ) {
		super();
		this.eServ = eServ;
		this.reiServ = reiServ;
	}
	
	
	public Handler createReimbursement = (ctx) ->{
		int employeeId = Integer.parseInt(ctx.pathParam("eid"));
		Reimbursement reimbursement = gson.fromJson(ctx.body(), Reimbursement.class);  
		Employee employee = eServ.getEmployeeById(employeeId);
		eServ.addReimbursementToEmployee(employee, reimbursement);
		ctx.status(201); 
		ctx.result(gson.toJson(reimbursement));
	};
	
	
	public Handler getReimbursementById = (ctx) ->{
		String id = ctx.pathParam("rid");
		Reimbursement reimbursement = reiServ.getReimbursementById(Integer.parseInt(id));	
		String json = gson.toJson(reimbursement);
		ctx.result(json);	
	};
	
	public Handler getAllReimbursements = (ctx) ->{
	    List<Reimbursement> reimbursements = reiServ.getAllReimbursements();
	    ctx.result(gson.toJson(reimbursements));
	};
	
	public Handler getReimbursementsByEmployee = (ctx) ->{
		int employeeId = Integer.parseInt(ctx.pathParam("eid"));
		Employee employee = eServ.getEmployeeById(employeeId);
	    List<Reimbursement> reimbursements = reiServ.getReimbursementsByEmployee(employee);
	    ctx.result(gson.toJson(reimbursements));
	};
	
	
	
	public Handler updateReimbursement = (ctx) ->{
		String reimbursementJson = ctx.body();
		Reimbursement reimbursement = gson.fromJson(reimbursementJson, Reimbursement.class);
		
		String statQ = ctx.queryParam("status");
		String noteQ = ctx.queryParam("notes");
		
		switch ((statQ != null) + "-" + (noteQ != null)) {
	    case "false-false":
	    	reiServ.updateReimbursement(reimbursement);	
			ctx.result(gson.toJson(reimbursement));
	        break;
	    case "false-true":
	    	// notes can be null but not status
	        break;
	    case "true-false":
	    	reiServ.addStatusUpdateToReimbursement(reimbursement, statQ, noteQ);
	    	ctx.result(gson.toJson(reimbursement));
	    	break;
	    case "true-true":
	    	reiServ.addStatusUpdateToReimbursement(reimbursement, statQ, noteQ);
	    	ctx.result(gson.toJson(reimbursement));
		    break;
	    default:
	        throw new RuntimeException("404: Something Broke");
		}
	};
	
	public Handler deleteReimbursement = (ctx) ->{
		String id = ctx.pathParam("rid");
		Reimbursement reimbursement = reiServ.getReimbursementById(Integer.parseInt(id));
		Boolean result = reiServ.deleteReimbursement(reimbursement);
		ctx.result(result.toString());
	};

}
