package dev.nauman.controllers;

import java.security.Permission;

import javax.servlet.http.Cookie;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.google.gson.Gson;

import dev.nauman.entities.Employee;
import dev.nauman.entities.Manager;
import dev.nauman.services.EmployeeService;
import dev.nauman.services.EmployeeServiceImpl;
import dev.nauman.services.ManagerService;
import dev.nauman.services.ManagerServiceImpl;
import io.javalin.http.Handler;

public class LoginController {
	private static EmployeeService eserv = EmployeeServiceImpl.getEmployeeServiceImpl();
	private static ManagerService mserv = ManagerServiceImpl.getManagerServiceImpl();
	private static String permission = "none";
	private static Gson gson = new Gson();
	
	public static Handler giveCookie = (ctx)->{
		Cookie cookie = new Cookie("name","AdamRanieri");
		ctx.cookie(cookie);
	};
	
	public static Handler createSession = (ctx) ->{
		Employee employee = gson.fromJson(ctx.body(), Employee.class);
		employee = eserv.login(employee.getUsername(), employee.getPassword());
		if(employee!=null) {
			ctx.sessionAttribute("permission",employee.isPermission());
			ctx.result(Boolean.toString(employee.isPermission()));
		}else {
			ctx.status(404);
			System.out.println("none");
		}
		
//		if(eserv.loginEmployee(employee.getUsername(), employee.getPassword()) == null) {
//			Manager manager = gson.fromJson(ctx.body(), Manager.class);
//			if(mserv.loginManager(manager.getUsername(), manager.getPassword()) ==null) {
//				ctx.status(404);
//				System.out.println("none");
//			}else {
//				manager = mserv.loginManager(manager.getUsername(), manager.getPassword());
//				permission = "manager";
//				System.out.println(manager);
//			}
//		} else {
//			employee = eserv.loginEmployee(employee.getUsername(), employee.getPassword());
//			ctx.result("employee");
//			permission = "manager";
//			System.out.println(employee);
//		}
	};
	
	public static Handler getUserInfo = (ctx) ->{
		String permission = ctx.sessionAttribute("permission");
//		if(permission == null) {
//			ctx.result("manager");
//		}else {
//			ctx.result(permission);
//		}
		System.out.println(gson.toJson(permission));
		ctx.result(permission);
		
	};
}
