package dev.alsabea.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.alsabea.entities.Employee;
import dev.alsabea.entities.Manager;
import dev.alsabea.entities.ReimbursementRequest;
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

		PersonWithRole p = new PersonWithRole();

		Credentials c = gs.fromJson(ctx.body(), Credentials.class);

		Employee e = eServ.retrieveByUsernameAndPassword(c.username, c.password);
		Manager m = null;
		if (e == null) {
			m = mServ.retrieveByUsernameAndPassword(c.username, c.password);
			if (m == null) {
				ctx.status(404);
				ctx.result("no such record exist");
			}
			
			p.mgr =  Cleaner.cleanInstance(m);
			p.role = "mgr";
			ctx.result(gs.toJson(p));

		} else {

			p.role = ("emp");
			p.emp = Cleaner.cleanInstance(e);
			ctx.result(gs.toJson(p));
		}

	};

	private static class PersonWithRole {

		String role;
		Employee emp;
		Manager mgr;

	}
	
	/*
	 * removes circular reference, manager and employee username and passwords.
	 */
	private static class Cleaner{
		static Employee cleanInstance(Employee e) {
			
			e.getMgr().setEmps(null);
			e.getMgr().setUsername(null);
			e.getMgr().setPassword(null);
			
			
			e.setReqs(cleanReqs(e.getReqs()));
			return e;
				
			
		}
		
		private static Manager cleanInstance(Manager m) {
			
			List<Employee> emps= new ArrayList<>();
			for (Employee e: m.getEmps()) {
				e.setUsername(null);
				e.setPassword(null);
				e.setMgr(null);
				e.setReqs(cleanReqs(e.getReqs()));
				emps.add(e);
			}
			m.setEmps(emps);
			return m;
			
		}
		
		private static  List<ReimbursementRequest> cleanReqs(List<ReimbursementRequest> reqs){
			for (ReimbursementRequest req: reqs) {
				req.setEmp(null);
				req.setMgr(null);
			}
			return reqs;
		}
		
	}

	private static class Credentials {

		String username;
		String password;

	}

}
