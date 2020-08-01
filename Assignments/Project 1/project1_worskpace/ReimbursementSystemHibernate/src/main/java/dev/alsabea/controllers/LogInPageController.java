package dev.alsabea.controllers;

import com.google.gson.Gson;

import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.services.EmployeeServices;
import dev.alsabea.services.ManagerServices;
import dev.alsabea.services.impl.EmployeeServicesImpl;
import dev.alsabea.services.impl.ManagerServicesImpl;
import io.javalin.http.Handler;

public class LogInPageController {

	private static EmployeeServices eServ = EmployeeServicesImpl.getInstance();
	private static ManagerServices mServ = ManagerServicesImpl.getInstance();

	private static Gson gs = new Gson();

	public static Handler getStaff = (ctx) -> {

		PersonWithRole p= new PersonWithRole();
		
		
		p = gs.fromJson(ctx.body(), PersonWithRole.class);

		Employee e = eServ.retrieveByUsernameAndPassword(p.username, p.password);
		Manager m = null;
		if (e == null) {
			m = mServ.retrieveByUsernameAndPassword(p.username, p.password);
			if (m == null) {
				ctx.status(404);
				ctx.result("no such record exist");
			}
			p.role="mgr";
			ctx.result(gs.toJson(p));

		} else {
			
			p.role=("emp");
			ctx.result(gs.toJson(p));
		}

	};
	
	
	
	
	
	
	private static class PersonWithRole {
		
		
		String username;
		String password;
		String role;

		PersonWithRole(){super();}
	}

	
	
	
	
	
	
	
	
	
	
	
}
