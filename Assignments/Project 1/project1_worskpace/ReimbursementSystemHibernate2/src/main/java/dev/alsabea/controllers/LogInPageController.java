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
			p.role = "mgr";
			p.mgr = m;
			ctx.result(gs.toJson(p));

		} else {

			p.role = ("emp");
			p.emp = e;
			ctx.result(gs.toJson(p));
		}

	};

	private static class PersonWithRole {

		String role;
		int mgrId;
		int empId;
		Employee emp;
		Manager mgr;
		
		@Override
		public String toString() {
			return "PersonWithRole [role=" + role + ", mgrId=" + mgrId + ", empId=" + empId + ", emp=" + emp + ", mgr="
					+ mgr + "]";
		}
		


	}

	private static class Credentials {

		String username;
		String password;

	}

}
