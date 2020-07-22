package dev.lee.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.lee.entities.Customer;
import dev.lee.services.CustomerService;
import dev.lee.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	
	public static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();
	
	public static Handler getAllCustomers = (ctx) -> {
		String username = ctx.queryParam("username");
		if(username!=null) {			
			Customer filtered = cserv.getCustomerByUsername(username);
			if(filtered == null) {
				ctx.status(404);
				ctx.result("Resource Not Found");
			}else {
				ctx.status(200);
				ctx.result(gson.toJson(filtered));
			}
		} else {
			Set<Customer> customers = cserv.getAllCustomers();
			String json = gson.toJson(customers);
			ctx.status(200);
			ctx.result(json);
		}
	};
	
	public static Handler getCustomerByCid = (ctx) -> {
		Integer cId =  Integer.parseInt(ctx.pathParam("cid"));
		Customer customer = cserv.getCustomerById(cId);
		if(customer == null) {
			ctx.status(404);
			ctx.result("Resource Not Found");
		}else {
			String json = gson.toJson(customer);
			ctx.status(200);
			ctx.result(json);
		}
	};
	
	public static Handler createCustomer = (ctx) -> {
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		Customer created = cserv.establishCustomer(customer);
		
		if (created != null) {
			ctx.status(201);
			ctx.result(gson.toJson(created));
		} else {
			ctx.status(409);
			ctx.result("Customer could not be created.");
		}	
	};
	
	public static Handler updateCustomer = (ctx) -> {
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		Customer updated = cserv.updateCustomer(customer);
		if (updated != null) {
			ctx.status(200);
			ctx.result(gson.toJson(customer));
		} else {
			ctx.status(409);
			ctx.result("Customer could not be updated.");
		}
		
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		Integer cId = Integer.parseInt(ctx.pathParam("cid"));
		if(cserv.deleteCustomer(cId)) {
			ctx.status(200);
			ctx.result("Customer deleted.");
		}else {
			ctx.status(409);
			ctx.result("Customer could not be deleted.");
		}
		
	};
	
}
