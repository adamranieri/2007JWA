package dev.rutan.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.rutan.entities.Customer;
import dev.rutan.services.CustomerService;
import dev.rutan.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {

	public static CustomerService cserv = new CustomerServiceImpl();
	
	public static Gson gson = new Gson();
	
	public static Handler getAllCustomers = (ctx)->{
		
		
		String capQ = ctx.queryParam("username");
		if(capQ != null) {
			Set<Customer> customers = cserv.getAllCustomersByUsername(capQ);
			ctx.status(200);
			ctx.result(gson.toJson(customers));
		} else {
			Set<Customer> customers = cserv.getAllCustomers();
			String json = gson.toJson(customers);
			ctx.status(200);
			ctx.result(json);
		}
	};
	
	public static Handler getCustomerById = (ctx)->{
		String id = ctx.pathParam("cId");
		Customer customer = cserv.getCustomerById(Integer.parseInt(id));
		if(customer.getcId() == Integer.parseInt(id))
			ctx.status(200);
		else
			ctx.status(404);
		String json = gson.toJson(customer);
		ctx.result(json);
	};
	
	public static Handler createCustomer = (ctx)->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.createCustomer(customer);
		if(customer.getcId() != 0)
			ctx.status(201);
		else
			ctx.status(404);
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler updateCustomer = (ctx)->{
		String customerJson = ctx.body();
		Customer customer = gson.fromJson(customerJson, Customer.class);
		cserv.updateCustomer(customer);
		ctx.status(200);
		ctx.result(gson.toJson(customer));
	};
	
	public static Handler deleteCustomer = (ctx)->{
		String id = ctx.pathParam("cId");
		Boolean result = cserv.deleteCustomer(Integer.parseInt(id));
		if(result)
			ctx.status(200);
		else
			ctx.status(404);
		ctx.result(result.toString());
	};
	
}
