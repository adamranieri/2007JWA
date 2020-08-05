package dev.alsabea.controllers;

import com.google.gson.Gson;

import dev.alsabea.controllers.utilities.Cleaner;
import dev.alsabea.controllers.utilities.Ids;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.services.EmployeeServices;
import dev.alsabea.services.ReimbursementRequestServices;
import dev.alsabea.services.impl.EmployeeServicesImpl;
import dev.alsabea.services.impl.ReimbursementRequestServicesImpl;
import io.javalin.http.Handler;

public class EmployeePageController {

	private static EmployeeServices eServ = EmployeeServicesImpl.getInstance();
	private static ReimbursementRequestServices rrServ= ReimbursementRequestServicesImpl.getInstance();
	private static Gson gs = new Gson();

//	public static Handler getEmployeeById = (ctx) -> {
//
//		int id = Integer.parseInt(ctx.pathParam("id"));
//
//		Employee emp = eServ.retrieveById(id);
//		
//		ctx.result(gs.toJson(emp));
//
//	};
	
	
	
	public static Handler createRequest = (ctx)->{
		
		String requestAsJson = ctx.body();
		ReimbursementRequest rr= gs.fromJson(requestAsJson, ReimbursementRequest.class);
		Ids empAndMgrIds= gs.fromJson(requestAsJson, Ids.class);
		
		rr.setEmp(new Employee(empAndMgrIds.getEmpId()));
		rr.setMgr(new Manager(empAndMgrIds.getMgrId()));
		
		if (rrServ.createInstance(rr)!=0) {
			ctx.status(200);
			
			Employee empCleaned= Cleaner.cleanInstance(eServ.retrieveById(rr.getEmp().getEmpId()));
			
			ctx.result(gs.toJson(empCleaned));
		
		}else {
			ctx.status(404);
		}
	};

	
	
	

	

	
}










