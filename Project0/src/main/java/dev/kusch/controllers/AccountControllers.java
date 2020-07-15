package dev.kusch.controllers;

import com.google.gson.Gson;

import dev.kusch.services.AccountServices;
import dev.kusch.services.AccountServicesImpl;
import io.javalin.http.Handler;

public class AccountControllers {

	public static AccountServices sserv = new AccountServicesImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllAccounts = (ctx) -> {
		
	};
	
	public static Handler getAccountById = (ctx) -> {
		
	};
	
	public static Handler createAccount = (ctx) -> {
		
	};
	
	public static Handler updateAccount = (ctx) -> {
		
	};
	
	public static Handler deleteAccount = (ctx) -> {
		
	};
	
}
