package dev.kurt.controllers;

import java.util.Set;
import com.google.gson.Gson;
import dev.kurt.entities.Account;
import dev.kurt.services.AccountService;
import dev.kurt.services.AccountServiceImpl;
import io.javalin.http.Handler;

public class AccountController {

	private static AccountService accServ = new AccountServiceImpl();
	private static Gson gson = new Gson();
	

	public static Handler getAccountsByCustomerId = (ctx) ->{
		int customerId = Integer.parseInt(ctx.pathParam("id"));	
		
		
		String greatQ = ctx.queryParam("balancegreaterthan");
		String lessQ = ctx.queryParam("balancelessthan");
		
		switch ((greatQ != null) + "-" + (lessQ != null)) {
	    case "false-false":
	    	Set<Account> accounts = accServ.getAccountsByCustomerId(customerId);
	        ctx.result(gson.toJson(accounts));
	        break;
	    case "false-true":
	        Set<Account> smallAccounts = accServ.getAccountsByBalanceLessThan(Integer.parseInt(lessQ), customerId);
	        ctx.result(gson.toJson(smallAccounts));
	        break;
	    case "true-false":
	    	Set<Account> largeAccounts = accServ.getAccountsByBalanceGreaterThan(Integer.parseInt(greatQ),customerId);
	    	ctx.result(gson.toJson(largeAccounts));
	    	break;
	    case "true-true":
	    	Set<Account> intersectAccounts = accServ.getAccountsBetweenBalances(Integer.parseInt(greatQ), Integer.parseInt(lessQ),customerId);
		    ctx.result(gson.toJson(intersectAccounts));
		    break;
	    default:
	        throw new RuntimeException("404: Something Broke");
		}
		
	};
	
	
	
	public static Handler getAllAccounts = (ctx) ->{
		
		
	};
	
	public static Handler getAccountById = (ctx)->{
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		Account account = accServ.getAccountById(accountId);
		String json = gson.toJson(account);
		
		ctx.result(json);
	};
	
	public static Handler createAccount = (ctx)->{
		int customerId = Integer.parseInt(ctx.pathParam("id"));	
		Account account = gson.fromJson(ctx.body(), Account.class); 
		account.setcId(customerId);
		accServ.openAccount(account);
		
		String json = gson.toJson(account);
		ctx.status(201);
		
		ctx.result(json);	
	};
	
	public static Handler updateAccount = (ctx)->{
		Account account = gson.fromJson(ctx.body(), Account.class);
		accServ.updateAccount(account);
		String json = gson.toJson(account);
		ctx.result(json);
	};
	
	
	public static Handler deleteAccount = (ctx) ->{
		int accountId = Integer.parseInt(ctx.pathParam("aid"));
		Boolean result = accServ.deleteAccountById(accountId);
		ctx.result(result.toString());
		
	};
	
	
	

}
