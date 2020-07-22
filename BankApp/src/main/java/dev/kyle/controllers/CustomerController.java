package dev.kyle.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.kyle.entities.Customer;
import dev.kyle.services.CustomerService;
import dev.kyle.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	private static Gson gson = new Gson();
	
	private static CustomerService cserv = new CustomerServiceImpl();
	
	public static Handler createCustomer = (ctx) -> {
		String CustomerJSON = ctx.body();
		Customer c = gson.fromJson(CustomerJSON, Customer.class);
		
		cserv.addCustomer(c);
		ctx.status(201);
		
		ctx.result(gson.toJson(c));
	};
	
	public static Handler getCustomerById = (ctx) -> {
		Customer c = cserv.getCustomerById(Integer.parseInt(ctx.pathParam("cid")));		
		if (c == null) {
			ctx.status(404);
		} else {
			ctx.status(200);
			String json = gson.toJson(c);
			ctx.result(json);	
		}	
	};
	
	public static Handler getAllCustomers = (ctx) -> {
		Set<Customer> Customers = cserv.getAllCustomers();
		String json = gson.toJson(Customers);
		
		String specName = ctx.queryParam("username");
		if (specName != null) {
			Customer nameMatch = cserv.getCustomerByName(specName);
			ctx.result(gson.toJson(nameMatch));
		} else {	
			if (Customers == null) {
				ctx.status(404);
			} else {
				ctx.status(200);
				ctx.result(json);
				// make sure the only to context objects are in the if and the else,
				// otherwise, it will default to the one outside.
			}
		}
		//ctx.result(json) << if query params are being processed, this *must* be taken out
	};
	
	public static Handler updateCustomer = (ctx) -> {
		String CustomerJSON = ctx.body();
		Customer c = gson.fromJson(CustomerJSON, Customer.class);
		
		cserv.updateCustomer(c);
		
		ctx.status(200);
		ctx.result(gson.toJson(c));
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		String strId = ctx.pathParam("cid");
		int intId = Integer.parseInt(strId);
		boolean res = cserv.deleteCustomerById(intId);
		
		if(res == false) {
			ctx.status(404);
		} else {
			ctx.status(200);
		}
	};
}
