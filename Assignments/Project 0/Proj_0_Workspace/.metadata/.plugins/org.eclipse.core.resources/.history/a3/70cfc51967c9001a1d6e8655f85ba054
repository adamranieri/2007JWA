package dev.alsabea.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.alsabea.entities.Account;
import dev.alsabea.entities.Customer;
import dev.alsabea.services.AccountServices;
import dev.alsabea.services.impl.AccountServicesImpl;
import io.javalin.http.Handler;

public class AccountsController {

	private static AccountServices aServices =AccountServicesImpl.getAccountServicesInstance();
	private static Gson gs= new Gson();
	
	public static Handler retrieveAllCustomerAccounts =  (ctx) ->{
		
		int id = Integer.parseInt(ctx.pathParam("id"));
		List<Account> accts = aServices.retrieveAllAccounts(id); 
		ctx.status(200);
		ctx.result(gs.toJson(accts));
		
	};
	
	public static Handler retrieveAnAccount =  (ctx) ->{
		int cid = Integer.parseInt(ctx.pathParam("id"));
		
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		

		Account a = aServices.retrieveById(aid);
		/*
		 * should never let an account get returned without checking if it belongs to the customer or not
		 */
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
		
		Account gottenFromDB= aServices.retrieveById(accountId);
		ctx.status(201);
		ctx.result(gs.toJson(gottenFromDB));
		
	};
	
	public static Handler deleteAnAccount =  (ctx) ->{
		//int cid = Integer.parseInt(ctx.pathParam("id"));
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		
		if (aServices.delete(aid)) {
			ctx.status(200);
			ctx.result("delete operation executed successfully");
		}
	};
	
	
	public static Handler updateAnAccount = (ctx) ->{
		int aid = Integer.parseInt(ctx.pathParam("aid"));
		int cid = Integer.parseInt(ctx.pathParam("id"));
		Account a= gs.fromJson(ctx.body(), Account.class);
		
		//extra checks in case the element inputted does not have this information
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

	
	
//	public static Handler updateCustomer = (ctx) ->{
//		int id = Integer.parseInt(ctx.pathParam("id"));
//		String custAsJson = ctx.body();
//		Customer c= gs.fromJson(custAsJson, Customer.class); 
//		c.setCustomerId(id);
//		if (cServices.update(c))
//			ctx.status(200);
//		ctx.result(gs.toJson(cServices.retrieveById(c.getCustomerId())));
//	};
}
