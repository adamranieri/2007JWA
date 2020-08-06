package dev.kusch.controllers;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;

import dev.kusch.dtos.LoginDTO;
import dev.kusch.entities.Employee;
import dev.kusch.entities.Manager;
import dev.kusch.services.EmployeeService;
import dev.kusch.services.EmployeeServiceImpl;
import dev.kusch.services.ManagerService;
import dev.kusch.services.ManagerServiceImpl;
import io.javalin.http.Handler;

public class LoginController {
	
	// USE A COOKIE TO AVOID ISSUES WITH JAVALIN SESSIO
	
	public static Handler loginHandler = (ctx) ->{
		EmployeeService eserv = new EmployeeServiceImpl();
		ManagerService mserv = new ManagerServiceImpl();
		
		Gson gson = new Gson();
		LoginDTO loginInfo=  gson.fromJson(ctx.body(), LoginDTO.class); 
		Manager man = mserv.loginManager(loginInfo);
		Employee emp = eserv.loginEmployee(loginInfo);
		
		if (man != null) {
			ctx.result(gson.toJson(man));
//			Cookie cookie = new Cookie("user", man.getUsername());
//			ctx.cookie(cookie);
//			System.out.println(man);
//			ctx.sessionAttribute("user", man);
//			ctx.sessionAttribute("isManager", true);
		} else if (emp != null){
			ctx.result(gson.toJson(emp));
//			Cookie cookie = new Cookie("user", emp.getUsername());
//			ctx.cookie(cookie);
//			System.out.println(emp);
//			ctx.sessionAttribute("user", emp);
//			ctx.sessionAttribute("isManager", false);
		} else {
			ctx.status(404);
		}
		
	};
	
	public static Handler getUserInfo = (ctx) ->{
		Gson gson = new Gson();
		//System.out.println(ctx.sessionAttribute("user"));
		if ((boolean) ctx.sessionAttribute("isManager")) {
			Manager man = ctx.sessionAttribute("user");
			ctx.result(gson.toJson(man));
		} else {
			Employee emp = ctx.sessionAttribute("user");
			ctx.result(gson.toJson(emp));
		}
		
	};

}
