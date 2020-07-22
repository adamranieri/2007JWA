package dev.kyle.controllers;


import java.util.Set;

import com.google.gson.Gson;

import dev.kyle.entities.Account;
import dev.kyle.services.AccountService;
import dev.kyle.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {
	private static Gson gson = new Gson();
	
	private static AccountService aserv = new AccountServiceImpl();
	
	public static Handler createAccount = (ctx) -> {
		String AccountJSON = ctx.body();
		Account a = gson.fromJson(AccountJSON, Account.class);
		
		aserv.addAccount(a);
			
		ctx.status(201);
		ctx.result(gson.toJson(a));
	};
	
	public static Handler getAccountById = (ctx) -> {
		String strId = ctx.pathParam("aid");
		int intId = Integer.parseInt(strId);
		Account a = aserv.getAccountById(intId);
		String json = gson.toJson(a);
		
		ctx.result(json);		
	};
	
	public static Handler getAllAccounts = (ctx) -> {
		Set<Account> Accounts = aserv.getAllAccounts();
		String json = gson.toJson(Accounts);
		
		String bgt = ctx.queryParam("balancegreaterthan");
		String blt = ctx.queryParam("balancelessthan");
		
		if (bgt != null) {
			Set<Account> accountsGreaterThan = aserv.balanceGreaterThan(Integer.parseInt(bgt));
			ctx.result(gson.toJson(accountsGreaterThan));
		} else {
			if(Accounts == null) {
				ctx.status(404);
			} else {
				ctx.result(json);
			}
		}
		
		if (blt != null) {
			Set<Account> accountsLessThan = aserv.balanceLessThan(Integer.parseInt(blt));
			ctx.result(gson.toJson(accountsLessThan));
		} else {
			if(Accounts == null) {
				ctx.status(404);
			} else {
				ctx.result(json);
			}
		}
	};
	
	public static Handler updateAccount = (ctx) -> {
		String AccountJSON = ctx.body();
		Account a = gson.fromJson(AccountJSON, Account.class);
		
		aserv.updateAccount(a);
		
		ctx.status(204);
		ctx.result(gson.toJson(a));
	};
	
	public static Handler deleteAccount = (ctx) -> {
		String strId = ctx.pathParam("aid");
		int intId = Integer.parseInt(strId);
		boolean res = aserv.deleteAccount(intId);
		
		if(res == false) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}	
	};
}
