package dev.schneider.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.schneider.entities.Customer;
//import dev.schneider.entities.Task;
import dev.schneider.services.CustomerService;
import dev.schneider.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {
	public static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();

	
	public static Handler createCustomer = (ctx) -> {
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.createCustomer(customer);
		
		ctx.result(gson.toJson(customer));
		ctx.status(201);
	};
	
	public static Handler getAllCustomers = (ctx) -> {
		Set<Customer> customers = cserv.getAllCustomers();
		String json = gson.toJson(customers);
		
		String nameQuery = ctx.queryParam("username");
		if (nameQuery != null) {
			Customer customer = cserv.getCustomerbyUsername(nameQuery);
			ctx.result(gson.toJson(customer));
		}
		else {
			ctx.result(json);
		}
		
		ctx.status(200);
			
	};
	
	public static Handler getCustomerByID = (ctx) -> {
		String id = ctx.pathParam("id");
		Customer customer = cserv.getCustomerByID(Integer.parseInt(id));
		if (customer == null) {
			ctx.result("not found");
			ctx.status(404);
		}else {
			String json = gson.toJson(customer);
			ctx.result(json);
			ctx.status(200);
		}
	};
	
	public static Handler updateCustomer = (ctx) -> {
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.updateCustomer(customer);
		String json = gson.toJson(customer);
		ctx.result(json);
		ctx.status(200);
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		String id = ctx.pathParam("id");
		Customer customer = cserv.getCustomerByID(Integer.parseInt(id));
		if (customer == null) {
			ctx.result("not found");
			ctx.status(404);
		}else {
			Boolean result = cserv.deleteCustomer(Integer.parseInt(id)); 
			ctx.result(result.toString());
			ctx.status(200);
		}
	};
	
	
	
}
