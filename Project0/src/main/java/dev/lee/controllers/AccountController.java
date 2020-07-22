package dev.lee.controllers;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

import dev.lee.entities.Account;
import dev.lee.services.AccountService;
import dev.lee.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {
	
	public  static AccountService aserv = new AccountServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllAccounts = (ctx) -> {
		String balanceGreaterThan = ctx.queryParam("balancegreaterthan");
		String balanceLessThan = ctx.queryParam("balancelessthan");
		
		if (balanceGreaterThan == null & balanceLessThan == null) {
			Set<Account> accounts = aserv.getAllAccounts();
			ctx.status(200);
			ctx.result(gson.toJson(accounts));
		}else {
			Set<Account> result = new HashSet<Account>();
			if(balanceGreaterThan != null & balanceLessThan != null) {
				result = aserv.getAccountsFilteredByBalance(-1, balanceGreaterThan, balanceLessThan);
			}
			
			if(balanceGreaterThan != null & balanceLessThan == null) {
				result = aserv.getAccountsFilteredByBalance(-1, balanceGreaterThan, "-1");
			}
			if(balanceLessThan != null & balanceGreaterThan == null) {
				result = aserv.getAccountsFilteredByBalance(-1, "-1", balanceLessThan);
			}
			ctx.status(200);
			ctx.result(gson.toJson(result));
		}
	};

	public static Handler getAccountByAid = (ctx)->{
		String aId = ctx.pathParam("aid");
		Account account = aserv.getAccountById(Integer.parseInt(aId));
		if (account != null) {
			ctx.status(200);
			ctx.result(gson.toJson(account));
		}else {
			ctx.status(404);
			ctx.result("Resource Not Found");
		}
	};
	
	public static Handler getAccountByCidAndAid = (ctx)->{
		String aId = ctx.pathParam("aid");
		String cId = ctx.pathParam("cid");
		Account account = aserv.getAccountByCidandAid(Integer.parseInt(cId), Integer.parseInt(aId));
		
		if (account != null) {
			ctx.status(200);
			ctx.result(gson.toJson(account));
		}else {
			ctx.status(404);
			ctx.result("Resource Not Found");
		}
	};
	
	public static Handler getAccountsByCid = (ctx)->{
		int cId = Integer.parseInt(ctx.pathParam("cid"));
		String balanceGreaterThan = ctx.queryParam("balancegreaterthan");
		String balanceLessThan = ctx.queryParam("balancelessthan");
		
		Set<Account> result = new HashSet<Account>();
		
		if (balanceGreaterThan == null && balanceLessThan == null) {
			result = aserv.getAccountsByCustomerId(cId);
		}else {
			
			
			if(balanceGreaterThan != null && balanceLessThan != null) {
				result = aserv.getAccountsFilteredByBalance(cId, balanceGreaterThan, balanceLessThan);
			}else if(balanceGreaterThan != null && balanceLessThan == null) {
				result = aserv.getAccountsFilteredByBalance(cId, balanceGreaterThan, "-1");
			}else if(balanceLessThan != null && balanceGreaterThan == null) {
				result = aserv.getAccountsFilteredByBalance(cId, "-1", balanceLessThan);
			}
		}
		
		if (result.size()!=0) {
			ctx.status(200);
			ctx.result(gson.toJson(result));
		}else {
			ctx.status(404);
			ctx.result("Resource Not Found");
		}
	};
	
	public static Handler createAccount = (ctx) -> {
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
				
		try {
			Account created = aserv.establishAccount(account);
			if (created == null) {
				ctx.status(409);
				ctx.result("Account could not be created.");
			}else {
				ctx.status(201);
				ctx.result(gson.toJson(created));
			}
		}catch(Exception e) {
			ctx.status(409);
			ctx.result("Account could not be created.");
		}
	};
	
	public static Handler updateAccount = (ctx) -> {
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		try {
			Account updated = aserv.updateAccount(account);
			if (updated != null) {
				ctx.status(200);
				ctx.result(gson.toJson(updated));
			}else {
				ctx.status(409);
				ctx.result("Account could not be updated.");
			}
		}catch(Exception e) {
			ctx.status(409);
			ctx.result("Account could not be updated.");
		}
	};
	
	public static Handler deleteAccount = (ctx) -> {
		Integer aId = Integer.parseInt(ctx.pathParam("aid"));
		if(aserv.deleteAccount(aId)) {
			ctx.status(200);
			ctx.result("Account deleted.");
		}else {
			ctx.status(409);
			ctx.result("Account could not be deleted.");
		}
	};

}
