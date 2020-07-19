package dev.kusch.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.kusch.entities.Customer;
import dev.kusch.services.CustomerServices;
import dev.kusch.services.CustomerServicesImpl;
import io.javalin.http.Handler;

public class CustomerControllers {
	public static CustomerServices cserv = new CustomerServicesImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllCustomers = (ctx) -> {
		Set<Customer> customers = cserv.getAllCustomers();
		String json = gson.toJson(customers);
		String username = ctx.queryParam("username");
		
		if (username != null) {
			Set<Customer> custWithUser = cserv.getCustomer(username);
			ctx.result(gson.toJson(custWithUser));
		} else {
			ctx.result(json);
		}
		
	};
	
	public static Handler getCustomerById = (ctx) -> {
		String id = ctx.pathParam("cid");
		Customer customer = cserv.getCustomer(Integer.parseInt(id));
		if (customer == null) {
			ctx.status(404);
		}
		ctx.result(gson.toJson(customer));
		
	};
	
	public static Handler createCustomer = (ctx) -> {
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.addCustomer(customer);
		ctx.status(201);
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler updateCustomer = (ctx) -> {
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		customer = cserv.updateCustomer(customer);
		if (customer == null) {
			ctx.status(404);
		}
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		String id = ctx.pathParam("cid");
		boolean result = cserv.deleteCustomer(Integer.parseInt(id));
	};
	
	
}
