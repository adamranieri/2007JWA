package dev.alsabea.controllers;

import com.google.gson.Gson;

import dev.alsabea.controllers.utilities.Cleaner;
import dev.alsabea.controllers.utilities.Ids;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.services.ManagerServices;
import dev.alsabea.services.ReimbursementRequestServices;
import dev.alsabea.services.impl.ManagerServicesImpl;
import dev.alsabea.services.impl.ReimbursementRequestServicesImpl;
import io.javalin.http.Handler;

public class ManagerPageController {
	private static ReimbursementRequestServices rrServ= ReimbursementRequestServicesImpl.getInstance();
	private static ManagerServices mServ = ManagerServicesImpl.getInstance();

	private static Gson gs = new Gson();
		

	public static Handler updateRequest = (ctx)->{
		


			//this is the updated Version
			ReimbursementRequest rr= gs.fromJson(ctx.body(), ReimbursementRequest.class);
			Ids ids= gs.fromJson(ctx.body(), Ids.class);
			
			rr.setRrId(ids.getRrId());
			rr.setEmp(new Employee(ids.getEmpId()));
			rr.setMgr(new Manager(ids.getMgrId()));
			
			rrServ.update(rr);
			Manager mCleaned =  Cleaner.cleanInstance(mServ.retrieveById(ids.getMgrId()));
			
			ctx.result(gs.toJson(mCleaned));
			
	};

	
	
	
	
	
}
