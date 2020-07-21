package dev.noah.controllers;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import dev.noah.entities.Account;
import dev.noah.services.AccountService;
import dev.noah.services.AccountServiceImpl;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class AccountController {

	private static AccountService aserv = new AccountServiceImpl();
	private static Gson gson = new Gson();

	public static Handler createAccount = (ctx) -> {
		Account account = gson.fromJson(ctx.body(), Account.class);
		aserv.createAccount(account);
		ctx.result(gson.toJson(account));
		if(account == null) {
			ctx.status(404);
		}else {
			ctx.status(201);
		}
	};

	public static Handler getAccount = (ctx) -> {
		String aId = ctx.pathParam("aid"); 
		Account account = aserv.getAccountById(Integer.parseInt(aId));
		ctx.result(gson.toJson(account));
		if(account == null) {
			ctx.status(404);
		}else {
			ctx.status(200);
		}
	}; 
	
	public static Handler getAllCustomerAccountsById = (ctx) -> {
		String customerId = ctx.pathParam("cid");
		Set<Account> accounts = aserv.getAllCustomerAccountsByCId(Integer.parseInt(customerId));
		String result1 = "";
		
		if(ctx.queryParam("balancegreaterthan") != null) { //finding all accounts with balances greater than amount
			result1 = ctx.queryParam("balancegreaterthan");
			accounts = aserv.getAccountsGreaterThanBal(true, Integer.parseInt(result1), accounts);
		}
		else if(ctx.queryParam("balancelessthan") != null) { //finding all accounts with balances less than amount 
			result1 = ctx.queryParam("balancelessthan");
			accounts = aserv.getAccountsGreaterThanBal(false, Integer.parseInt(result1), accounts);
		}
		else if(ctx.queryParam("balancelessthan") != null && ctx.queryParam("balancegreaterthan") != null) { //finding all account balances within two amounts
			result1 = ctx.queryParam("balancelessthan");
			String result2 = ctx.queryParam("balancegreaterthan");
			aserv.getBalancesByDifference(Double.parseDouble(result2), Double.parseDouble(result1), accounts);
		}
		ctx.result(gson.toJson(accounts));
		
		if(accounts == null) {
			ctx.status(404);
		}
		else {
			ctx.status(200);
		}
	};
	
	public static Handler updateAccount = (ctx) -> {
		Account account = gson.fromJson(ctx.body(), Account.class);
		aserv.updateAccount(account);
		ctx.result(gson.toJson(account));
		
		if(account == null) {
			ctx.status(404);
		}
		else {
			ctx.status(200);
		}
	};
	
	public static Handler deleteAccount = (ctx) -> {
		String account = ctx.pathParam("aid");
		boolean result = aserv.deleteAccountById(Integer.parseInt(account));
		ctx.result(gson.toJson(result));
		if(result == false) {
			ctx.status(404);
		}
		else {
			ctx.status(200);
		}
	};

}
