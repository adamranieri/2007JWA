package dev.rutan.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.rutan.entities.Account;
import dev.rutan.services.AccountService;
import dev.rutan.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {

	public static AccountService aserv = new AccountServiceImpl();
	

	
	public static Gson gson = new Gson();
	
	public static Handler getAllAccounts = (ctx)->{
		String id = ctx.pathParam("cId");
		String lessQ = ctx.queryParam("balancelessthan");
		String greaterQ = ctx.queryParam("balancegreaterthan");
		
		if(lessQ != null && greaterQ != null) {
			Set<Account> accounts = aserv.getAccountsBetween(Integer.parseInt(greaterQ), Integer.parseInt(lessQ), Integer.parseInt(id));
			ctx.result(gson.toJson(accounts));
		} else if (lessQ != null) {
			Set<Account> accounts = aserv.getAccountsLessThan(Integer.parseInt(lessQ), Integer.parseInt(id));
			ctx.result(gson.toJson(accounts));
		} else if (greaterQ != null) {
			Set<Account> accounts = aserv.getAccountsGreaterThan(Integer.parseInt(greaterQ), Integer.parseInt(id));
			ctx.result(gson.toJson(accounts));
		} else {
			Set<Account> accounts = aserv.getAllAccountsByCustomerId(Integer.parseInt(id));
			ctx.result(gson.toJson(accounts));
		}
	};
	
	public static Handler getAccountById = (ctx)->{
		String id = ctx.pathParam("aId");
		Account account = aserv.getAccountById(Integer.parseInt(id));
		if (account.getaId() == (Integer.parseInt(id)))
			ctx.status(200);
		else
			ctx.status(400);
		ctx.result(gson.toJson(account));
	};
	
	public static Handler createAccount = (ctx)->{
		String id = ctx.pathParam("cId");
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		account.setcId(Integer.parseInt(id));
		aserv.createAccount(account);
		if (account.getaId() != 0) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
		ctx.result(gson.toJson(account));
	};
	
	public static Handler updateAccount = (ctx)->{
		String id = ctx.pathParam("cId");
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		account.setcId(Integer.parseInt(id));
		aserv.updateAccount(account);
		if (account.getBalance() >= 0) {
			ctx.status(200);
		} else {
			ctx.status(400);
		}
		ctx.result(gson.toJson(account));
	};
	
	public static Handler deleteAccount = (ctx)->{
		String id = ctx.pathParam("aId");
		Boolean result = aserv.deleteAccount(Integer.parseInt(id));
		if(result) {
			ctx.status(200);
		} else
			ctx.status(404);
		ctx.result(result.toString());
	};
	
}
