package dev.schneider.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.schneider.entities.Account;
import dev.schneider.entities.Customer;
import dev.schneider.services.AccountService;
import dev.schneider.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {

	public static AccountService aserv = new AccountServiceImpl();
	private static Gson gson = new Gson();

	public static Handler createAccount = (ctx) -> {
		int cID = Integer.parseInt(ctx.pathParam("id"));		
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		account.setcID(cID);
		aserv.addAccount(account);
		
		ctx.result(gson.toJson(account));
		ctx.status(201);
	};
	
	public static Handler getAllCustomerAccounts = (ctx) -> {
		String id = ctx.pathParam("id");
		Set<Account> accounts = aserv.getAccountsByCustomer(Integer.parseInt(id));
		String json = gson.toJson(accounts);
		String lessThanQ = ctx.queryParam("balancelessthan");
		Set<Account> subsetAccounts = filter(lessThanQ, accounts, "less");
		String greaterThanQ = ctx.queryParam("balancegreaterthan");
		subsetAccounts = filter(greaterThanQ, subsetAccounts, "greater");
		ctx.result(gson.toJson(subsetAccounts));
		ctx.status(200);
	};
	
	public static Set<Account> filter(String query, Set<Account> bigSet, String isGreaterOrLess) {
		Set<Account> accounts;
		if (query != null){
			if (isGreaterOrLess == "less") {
				accounts = aserv.balanceLessThan(bigSet, Integer.parseInt(query));
			}
			else {
				accounts = aserv.balanceGreaterThan(bigSet, Integer.parseInt(query));
			}
		}
		else {
			accounts = bigSet;
		}
		return accounts;
	}
	
	
	public static Handler getAccountByID = (ctx) -> {
		String id = ctx.pathParam("aID");
		Account account = aserv.getAccountById(Integer.parseInt(id));
		if (account == null) {
			ctx.result("not found");
			ctx.status(404);
		}else {
			String json = gson.toJson(account);
			ctx.result(json);
			ctx.status(200);
		}
	};
	
	public static Handler updateAccount = (ctx) -> {
		String accountJson = ctx.body();
		Account account = gson.fromJson(accountJson, Account.class);
		try {		
			aserv.updateAccount(account);
			String json = gson.toJson(account);
			ctx.result(json);
			ctx.status(200);
		}
		catch(Exception e){
			ctx.result("Balance cannot be negative");
			ctx.status(400);
		}
	};
	
	public static Handler deleteAccount = (ctx) -> {
		String id = ctx.pathParam("aID");
		Account account = aserv.getAccountById(Integer.parseInt(id));
		if (account == null) {
			ctx.result("not found");
			ctx.status(404);
		}else {
			Boolean result = aserv.deleteAccount(Integer.parseInt(id)); 
			ctx.result(result.toString());
			ctx.status(200);
		}
	};
	
}
