package dev.laurent.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.laurent.entities.Account;
import dev.laurent.entities.Customer;
import dev.laurent.services.CustomerService;
import dev.laurent.services.CustomerServiceImpl;
import dev.laurent.services.AccountService;
import dev.laurent.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {
	private static AccountService aserv = new AccountServiceImpl();
	private static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllAccountsFromCustomerId = (ctx)->{
		int customerId = Integer.parseInt(ctx.pathParam("id"));
		Set<Account> accounts = aserv.getAccountsByCustomerId(customerId);
		String json = gson.toJson(accounts);
		ctx.result(json);
		
		String balanceQL = ctx.queryParam("balancelessthan");
		if(balanceQL != null) {
			Set<Account> smallAccounts = aserv.getAccountsByBalanceLessThan(Integer.parseInt(balanceQL));
			ctx.result(gson.toJson(smallAccounts));
		}else {
			ctx.result(json);	
		}
		
		String balanceQG = ctx.queryParam("balancegreaterthan");
		if(balanceQG != null) {
			Set<Account> largeAccounts = aserv.getAccountsByBalanceGreaterThan(Integer.parseInt(balanceQG));
			ctx.result(gson.toJson(largeAccounts));
		}else {
			ctx.result(json);	
		}
	};
	
	public static Handler getAccountbyId = (ctx)->{
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		Account account = aserv.getAccountById(accountId);
		String json = gson.toJson(account);
		ctx.result(json);
	};
	
	public static Handler createAccount = (ctx)->{
		int customerId = Integer.parseInt(ctx.pathParam("id"));
		Account account = gson.fromJson(ctx.body(), Account.class);
		account.setcId(customerId);
		aserv.addAccount(account);
		String json = gson.toJson(account);
		ctx.status(201);
		ctx.result(json);
	};
	
	public static Handler updateAccount = (ctx)->{
		Account account = gson.fromJson(ctx.body(), Account.class);
		aserv.updateAccount(account);
		String json = gson.toJson(account);
		ctx.result(json);
	};
	
	public static Handler deleteAccount = (ctx)->{
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		Boolean result = aserv.deleteAccount(accountId);
		ctx.result(result.toString());
	};
}
