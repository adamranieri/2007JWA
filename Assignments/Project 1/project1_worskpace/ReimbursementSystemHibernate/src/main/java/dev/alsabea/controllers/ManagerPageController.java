package dev.alsabea.controllers;

import com.google.gson.Gson;

import dev.alsabea.controllers.utilities.Cleaner;
import dev.alsabea.controllers.utilities.Ids;
import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;
import dev.alsabea.services.EmployeeServices;
import dev.alsabea.services.ManagerServices;
import dev.alsabea.services.ReimbursementRequestServices;
import dev.alsabea.services.impl.EmployeeServicesImpl;
import dev.alsabea.services.impl.ManagerServicesImpl;
import dev.alsabea.services.impl.ReimbursementRequestServicesImpl;
import io.javalin.http.Handler;

public class ManagerPageController {
	private static ReimbursementRequestServices rrServ= ReimbursementRequestServicesImpl.getInstance();
	private static ManagerServices mServ = ManagerServicesImpl.getInstance();
	private static EmployeeServices eServ = EmployeeServicesImpl.getInstance();
	
	private static Gson gs = new Gson();
		

	public static Handler updateRequest = (ctx)->{
		


			//this is the updated Version
			ReimbursementRequest rrNew= gs.fromJson(ctx.body(), ReimbursementRequest.class);
			Ids ids= gs.fromJson(ctx.body(), Ids.class);
			
			ReimbursementRequest rrTemp=rrServ.retrieveById(ids.getRrId());
			rrNew.setEmp(rrTemp.getEmp());
			rrNew.setMgr(rrTemp.getMgr());
			
			rrServ.update(rrNew);
			Employee eCleaned =  Cleaner.cleanInstance(eServ.retrieveById(rrNew.getEmp().getEmpId()));
			
			ctx.result(gs.toJson(eCleaned));
			
	};

	
	
	
	
	
}
