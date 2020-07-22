package dev.dave.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.dave.entities.Accounts;
import dev.dave.services.AccountsServices;
import dev.dave.services.AccountsServicesImpl;
import io.javalin.http.Handler;

public class AccountsController {
	
	public static AccountsServices aserv = AccountsServicesImpl.getAccountsServices();
	
	private static Gson gson = new Gson();
	
	// CREATE
	
	public static Handler createAccount= (ctx)->{
		
		String accountJson = ctx.body();
		Accounts account = gson.fromJson(accountJson, Accounts.class);
		aserv.openAccount(account);
		ctx.status(201);
		ctx.result(gson.toJson(account));
	};
	
	// READ
	
	public static Handler getAccountByID= (ctx)->{
		
		String id = ctx.pathParam("aid");
		Accounts account = aserv.pullUpAccount(Integer.parseInt(id));
		
		if (account == null) { 
			ctx.status(404);	
		}else {
			String json = gson.toJson(account); 
			ctx.result(json); 
		}
	};
	
	public static Handler getAccountByUserID= (ctx)->{
		
		int custid = Integer.parseInt(ctx.pathParam("cid"));
		Set<Accounts> accountslist = aserv.listAccounts(custid);
		String json = gson.toJson(accountslist);
		ctx.result(json);
	};
	
	// UPDATE
	
	public static Handler updateAccount= (ctx) ->{
		
		String accountJson = ctx.body();
		Accounts account = gson.fromJson(accountJson, Accounts.class);
		aserv.updateAccount(account);
		ctx.result(gson.toJson(account));
	};
	
	// DELETE
	
	public static Handler deleteAccount= (ctx) ->{
		
		String id = ctx.pathParam("aid");
		Boolean result = aserv.cancelAccount(Integer.parseInt(id));
		ctx.result(result.toString());
	};
	
	public static Handler deleteAllCustAccounts= (ctx) ->{
		
		String id = ctx.pathParam("cid");
		Boolean result = aserv.cancelAllAccounts(Integer.parseInt(id));
		ctx.result(result.toString());
	};
	
	// QUERY PARAM CONTROLLER

}
