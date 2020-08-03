package dev.ranieri.controllers;

import javax.servlet.http.Cookie;

import com.google.gson.Gson;

import dev.ranieri.dtos.LoginDTO;
import dev.ranieri.entities.User;
import dev.ranieri.services.FakeUserService;
import dev.ranieri.services.UserService;
import io.javalin.http.Handler;

public class LoginController {
	
	UserService userService = new FakeUserService();
	
	public Handler giveCookie = (ctx) ->{
		
		// This will attacha cookie to the http response
		// this cookie (small text file) will be available on the front end
		// anytime someone is accessing your web application
		Cookie cookie = new Cookie("name","AdamRanieri");
		ctx.cookie(cookie);
	};

	public Handler createSession = (ctx) ->{
		Gson gson = new Gson();
		LoginDTO loginInfo=  gson.fromJson(ctx.body(), LoginDTO.class); 
		User user = userService.loginUser(loginInfo); // gets you back user
		ctx.sessionAttribute("loggedInAs", user); // this stores the information in a sesion object in Java
		// A session is shared across pages by a single user
		// Each different user gets its own session
		
	};
	
	public Handler getUserInfo = (ctx) ->{
		Gson gson = new Gson();
		User u = ctx.sessionAttribute("loggedInAs");
		ctx.result(gson.toJson(u));
		
	};
	
	
}
