package dev.kurt.controllers;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;

import dev.kurt.dtos.LoginDTO;
import dev.kurt.entities.User;
import dev.kurt.services.UserService;
import io.javalin.http.Handler;

public class LoginController {
	
	private UserService uServ;
	private Gson gson = new Gson();
	
	public LoginController(UserService uServ) {
		super();
		this.uServ = uServ;
	}
	
	
	public Handler giveCookie = (ctx) ->{
		Cookie cookie = new Cookie("name","AdamRanieri");
		ctx.cookie(cookie);
	};
	
	public Handler createSession = (ctx) ->{
		Gson gson = new Gson();
		LoginDTO loginInfo =  gson.fromJson(ctx.body(), LoginDTO.class); 
		System.out.println("before serv");
		User user = uServ.loginUser(loginInfo);
		System.out.println("after serv");
		ctx.sessionAttribute("loggedInAs", user); 
		System.out.println(user.getClass());
	};

	public Handler getUserInfo = (ctx) ->{
		User u = ctx.sessionAttribute("loggedInAs");
		ctx.result(gson.toJson(u));
	};
}
