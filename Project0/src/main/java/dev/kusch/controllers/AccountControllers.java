package dev.kusch.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.kusch.services.AccountServices;
import dev.kusch.services.AccountServicesImpl;
import dev.kusch.entities.Account;
import dev.kusch.entities.Customer;
import io.javalin.http.Handler;

public class AccountControllers {

	public static AccountServices aserv = new AccountServicesImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllAccountsForCustomer = (ctx) -> {
		int custId = Integer.parseInt(ctx.pathParam("cid"));
		Set<Account> accounts = aserv.getAccountsByCustomer(custId);
		if (accounts == null) {
			ctx.status(404);
			return;
		}
		
		String lessThan = ctx.queryParam("balancelessthan");
		String greaterThan = ctx.queryParam("balancegreaterthan");
		
		if (lessThan != null) {
			int upperBound = Integer.parseInt(lessThan);
			if (greaterThan != null) {
				int lowerBound = Integer.parseInt(greaterThan);
				Set<Account> between = aserv.getAccountsBetween(lowerBound, upperBound, custId);
				ctx.result(gson.toJson(between));
				return;
			}
			Set<Account> less = aserv.getAccountsLessThan(upperBound, custId);
			ctx.result(gson.toJson(less));
			return;
		}
		
		if (greaterThan != null) {
			int lowerBound = Integer.parseInt(greaterThan);
			Set<Account> greater = aserv.getAccountsGreaterThan(lowerBound, custId);
			ctx.result(gson.toJson(greater));
			return;
		}
		
		ctx.result(gson.toJson(accounts));
	};
	
	public static Handler getAccountById = (ctx) -> {
		String id = ctx.pathParam("aid");
		Account account = aserv.getAccount(Integer.parseInt(id));
		if (account == null) {
			ctx.status(404);
		}
		ctx.result(gson.toJson(account));
	};
	
	public static Handler createAccount = (ctx) -> {
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		aserv.startAccount(account);
		ctx.status(201);
		ctx.result(gson.toJson(account));
	};
	
	public static Handler updateAccount = (ctx) -> {
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		account = aserv.updateAccount(account);
		if (account == null) {
			ctx.status(404);
		}
		ctx.result(gson.toJson(account));
	};
	
	public static Handler deleteAccount = (ctx) -> {
		String id = ctx.pathParam("aid");
		boolean result = aserv.deleteAccount(Integer.parseInt(id));
		if (result == false) {
			ctx.status(404);
		}
	};
	
}
