package dev.kurt.controllers;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;

import dev.kurt.dtos.LoginDTO;
import dev.kurt.entities.Employee;
import dev.kurt.entities.Manager;
import dev.kurt.entities.User;
import dev.kurt.services.EmployeeService;
import dev.kurt.services.ManagerService;
import dev.kurt.services.UserService;
import io.javalin.http.Handler;

public class LoginController {
	
	private UserService uServ;
	private Gson gson = new Gson();
	
	public LoginController(UserService uServ, EmployeeService empServ, ManagerService manServ) {
		super();
		this.uServ = uServ;
	}
	
	public Handler createSession = (ctx) ->{
		Gson gson = new Gson();
		LoginDTO loginInfo =  gson.fromJson(ctx.body(), LoginDTO.class); 
		try {
			User user = uServ.loginUser(loginInfo);
			ctx.sessionAttribute("loggedInAs", user); 
			if(user instanceof Employee) {
				ctx.redirect("/empportal.html");
				
			}
			if(user instanceof Manager) {
				ctx.redirect("/managerportal.html");
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	};

	public Handler getUserInfo = (ctx) ->{
		User u = ctx.sessionAttribute("loggedInAs");
		ctx.result(gson.toJson(u));
	};
}
