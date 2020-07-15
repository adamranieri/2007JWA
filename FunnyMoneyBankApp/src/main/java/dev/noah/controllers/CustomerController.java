package dev.noah.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.noah.entities.Customer;
import dev.noah.services.CustomerService;
import dev.noah.services.CustomerServiceImpl;
import io.javalin.http.Handler;

public class CustomerController {

	private static CustomerService cserv = new CustomerServiceImpl();
	private static Gson gson = new Gson();

	public static Handler createCustomer = (ctx) -> {
		Customer cus = gson.fromJson(ctx.body(), Customer.class);
		cserv.createCustomer(cus);
		ctx.status(201);
		ctx.result(gson.toJson(cus));
	};
	
	public static Handler getCustomerById = (ctx) ->{
		String id = ctx.pathParam("cid");
		Customer cus = cserv.getCustomerByCId(Integer.parseInt(id));
		ctx.result(gson.toJson(cus));
		ctx.status(200);
	};
	
	public static Handler getAllCustomers = (ctx) -> {
		Set<Customer> getCustomers = cserv.getAllCustomers();
		ctx.result(gson.toJson(getCustomers));
		ctx.status(200);
		
	};
	
	public static Handler updateCustomer = (ctx) -> {
		Customer cus = gson.fromJson(ctx.body(), Customer.class);
		cserv.updateCustomer(cus);
		ctx.result(gson.toJson(cus));
		ctx.status(200);
		
	};
	
	public static Handler deleteCustomer = (ctx) -> {
		String id = ctx.pathParam("cid");
		boolean result = cserv.deleteCustomerByCId(Integer.parseInt(id));
		ctx.status(200);
	};
}
