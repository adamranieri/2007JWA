package dev.alsabea.controllers;

import java.util.List;

import com.google.gson.Gson;

import dev.alsabea.entities.Customer;
import dev.alsabea.services.CustomerServices;
import dev.alsabea.services.impl.CustomerServicesImpl;
import io.javalin.http.Handler;

public class CustomersController {

	private static CustomerServices cServices = CustomerServicesImpl.getCustomerServicesInstance();
	private static Gson gs= new Gson();
	
	public static Handler retrieveAllCustomers = (ctx) ->{
					
		
		String usernameQ = ctx.queryParam("username");
		
		if(usernameQ != null) {
			List<Customer> list= cServices.retrieveByUsername(usernameQ);
			ctx.result(gs.toJson(list));
		}else {
			List<Customer> customers = cServices.retrieveAll();	
			ctx.result( gs.toJson(customers));	
		}
			
	};
	public static Handler addACustomer = (ctx) -> {
		String custAsJson = ctx.body();
		Customer c= gs.fromJson(custAsJson, Customer.class); 
		int id = cServices.create(c);
		
		Customer customerGottenFromDB= cServices.retrieveById(id);
		ctx.status(201); 
		
		ctx.result(gs.toJson(customerGottenFromDB));
	};
	
	
	public static Handler deleteCustomer = (ctx) -> {
		String id = ctx.pathParam("cid");
		if (cServices.delete(Integer.parseInt(id))) {
			ctx.status(201);
			ctx.result("delete operation executed successfully");
		}
		
		
	};
	
	
	public static Handler updateCustomer = (ctx) ->{
		String custAsJson = ctx.body();
		Customer c= gs.fromJson(custAsJson, Customer.class); 
		if (cServices.update(c))
			ctx.status(201);
		ctx.result(gs.toJson(cServices.retrieveById(c.getCustomerId())));
	};
	
	
	/*
	 * reliant on
	 */
	public static Handler retrieveACustomer = (ctx) ->{
		int id = Integer.parseInt(ctx.pathParam("cid"));
		Customer c = cServices.retrieveById(id);
		
		/*
		 * it shouldn't be handled here
		 */
		
		c.setAccounts(cServices.getCustomerAccounts(id)); 
		
		ctx.result(gs.toJson(c));
		
		
		
	};
	
	
	
	
	
}
