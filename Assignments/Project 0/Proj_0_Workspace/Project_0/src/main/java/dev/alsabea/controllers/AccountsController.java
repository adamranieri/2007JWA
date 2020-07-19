package dev.alsabea.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.alsabea.entities.Account;
import dev.alsabea.services.AccountServices;
import dev.alsabea.services.impl.AccountServicesImpl;
import io.javalin.http.Handler;

public class AccountsController {

	private static AccountServices aServices =AccountServicesImpl.getAccountServicesInstance();
	private static Gson gs= new Gson();
	
	/**
	 * this function has two optional query parameters
	 * @returns Accounts with balance less than the specified -- if balancelessthan query is used
	 * @returns Accounts with balance greater than the specified -- if balancegreaterthan query is used
	 * @returns Accounts with balance in the specified range 
	 * -- if both (balancelessthan  and balancegreaterthan) queries are used
	 * @returns 
	 * 	all customer accounts if no query parameter is used
	 *
	 */
	
	public static Handler retrieveAllCustomerAccounts =  (ctx) ->{
		
		int cid = Integer.parseInt(ctx.pathParam("id"));
		
		String q1= ctx.queryParam("balancelessthan");
		String q2=ctx.queryParam("balancegreaterthan");
		
		int q1AsNum=0,  q2AsNum=0;
		List<Account> accts = aServices.retrieveAllAccounts(cid); 
		//if both queries are used  ex: balancelessthan = ? & balancegreaterthan = ?
		if (q1!=null & q2!=null) {
			q1AsNum = Integer.parseInt(q1);
			q2AsNum = Integer.parseInt(q2);
			accts =  aServices.balanceLessThan(q1AsNum, accts);
			accts =  aServices.balanceGreaterThan(q2AsNum, accts);
		
		//if only balancelessthan query is used
		} else if (q1!=null & q2==null) {
			q1AsNum = Integer.parseInt(q1);
			accts =  aServices.balanceLessThan( q1AsNum, accts);
		
			// if only balancegreaterthan query is used
		} else if (q1 == null & q2!=null) {
			q2AsNum = Integer.parseInt(q2);
			accts =  aServices.balanceGreaterThan(q2AsNum, accts);
		} else /* (q1 == null & q2 ==null)*/{
			//do nothing (return all customer's accounts)
		}
		
		
		
		ctx.status(200);
		ctx.result(gs.toJson(accts));
		
	};
	
	public static Handler retrieveAnAccount =  (ctx) ->{
		int cid = Integer.parseInt(ctx.pathParam("id"));
		
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		

		Account a = aServices.retrieveById(aid);
		
		//check if the customer actually owns the account, this is determined by 
		// checking if customer_id of the account matches the customer_id in the path
		if (Integer.compare(a.getCustomerId(), cid) == 0) {
			ctx.status(200);
			ctx.result(gs.toJson(a));
		} else {
			ctx.status(404);
			ctx.result("Customer does not have such account");
		}

	};
	
	
	public static Handler createAnAccount = (ctx) ->{
		
		int cid = Integer.parseInt(ctx.pathParam("id"));
		Account a = gs.fromJson(ctx.body(), Account.class);
		a.setCustomerId(cid);
		int accountId = aServices.create(a);
		
		//after we create the account object and insert it, 
		// we fetch it from db to return it to the web page
		Account gottenFromDB= aServices.retrieveById(accountId);
		ctx.status(201);
		ctx.result(gs.toJson(gottenFromDB));
		
	};
	
	public static Handler deleteAnAccount =  (ctx) ->{
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		if (aServices.delete(aid)) {
			ctx.status(200);
			ctx.result("delete operation executed successfully");
		} else  {
			ctx.status(404);
			ctx.result("No account with this ID");
		}
	};
	
	
	public static Handler updateAnAccount = (ctx) ->{
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		int cid = Integer.parseInt(ctx.pathParam("id"));
		Account a= gs.fromJson(ctx.body(), Account.class);
		
		//extra checks in case the element inputed does not have this information
		a.setCustomerId(cid);
		a.setAccountId(aid);
		
		if (aServices.update(a)){
			Account gottenFromDB = aServices.retrieveById(aid);
			ctx.status(200);
			ctx.result(gs.toJson(gottenFromDB));
		}
		else {
			ctx.status(404);
			ctx.result("no such account exist, update failed");
		}
	};
	
	
	
}



